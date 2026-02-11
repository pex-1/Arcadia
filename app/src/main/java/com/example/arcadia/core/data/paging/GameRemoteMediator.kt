package com.example.arcadia.core.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import coil.network.HttpException
import com.example.arcadia.core.database.entity.GameEntity
import com.example.arcadia.core.domain.datasource.LocalGameDataSource
import com.example.arcadia.core.domain.datasource.RemoteGameDataSource
import com.example.arcadia.core.domain.util.Result
import okio.IOException

@OptIn(ExperimentalPagingApi::class)
class GameRemoteMediator(
    private val remoteGameDataSource: RemoteGameDataSource,
    private val localGameDataSource: LocalGameDataSource,
    private val genreIds: Set<Int>
) : RemoteMediator<Int, GameEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GameEntity>
    ): MediatorResult {

        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.roomId / state.config.pageSize) + 1
                    }
                }
            }

            when (val response =
                remoteGameDataSource.fetchGames(currentPage, genreIds.toList().map { it.toString() })) {
                is Result.Success -> {
                    if (loadType == LoadType.REFRESH) {
                        localGameDataSource.deleteAllGames()
                    }

                    when (val upsertResult = localGameDataSource.upsertGames(response.data)) {
                        is Result.Error -> MediatorResult.Error(Throwable(upsertResult.error.toString()))
                        is Result.Success -> {
                            MediatorResult.Success(endOfPaginationReached = response.data.isEmpty())
                        }
                    }
                }

                is Result.Error -> {
                    MediatorResult.Error(Throwable(response.error.toString()))
                }
            }

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

}