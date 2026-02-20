package com.example.arcadia.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.arcadia.core.data.paging.GameRemoteMediator
import com.example.arcadia.core.database.dao.GameDao
import com.example.arcadia.core.database.entity.GameEntity
import com.example.arcadia.core.database.mappers.toDomain
import com.example.arcadia.core.network.mappers.toDomain
import com.example.arcadia.core.database.util.DatabaseConstants
import com.example.arcadia.core.domain.SettingsDataStore
import com.example.arcadia.core.domain.datasource.LocalGameDataSource
import com.example.arcadia.core.domain.datasource.RemoteGameDataSource
import com.example.arcadia.core.domain.model.Game
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GamePagingRepositoryImpl @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
    private val gameDao: GameDao,
    private val remoteDataSource: RemoteGameDataSource,
    private val localDataSource: LocalGameDataSource
) : GamePagingRepository {

    @OptIn(ExperimentalPagingApi::class, ExperimentalCoroutinesApi::class)
    override fun getPagedGames(): Flow<PagingData<Game>> {
        return settingsDataStore.selectedGenreIds.flatMapLatest { genreIds ->
            val pagingSourceFactory = { gameDao.getPagedGames() }

            Pager(
                config = PagingConfig(
                    pageSize = DatabaseConstants.ITEMS_PER_PAGE,
                    prefetchDistance = DatabaseConstants.PREFETCH_DISTANCE,
                    initialLoadSize = DatabaseConstants.INITIAL_LOAD_SIZE
                ),
                remoteMediator = GameRemoteMediator(
                    remoteGameDataSource = remoteDataSource,
                    localGameDataSource = localDataSource,
                    genreIds = genreIds
                ),
                pagingSourceFactory = pagingSourceFactory
            ).flow.map { pagingData ->
                pagingData.map(GameEntity::toDomain)
            }
        }
    }
}