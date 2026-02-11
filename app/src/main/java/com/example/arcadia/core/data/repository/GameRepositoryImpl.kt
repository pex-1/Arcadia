package com.example.arcadia.core.data.repository

import com.example.arcadia.core.domain.datasource.LocalGameDataSource
import com.example.arcadia.core.domain.datasource.RemoteGameDataSource
import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.repository.GameRepository
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.EmptyResult
import com.example.arcadia.core.domain.util.Result
import com.example.arcadia.core.domain.util.asEmptyDataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteGameDataSource,
    private val localDataSource: LocalGameDataSource,
) : GameRepository {
    override suspend fun upsertGames(games: List<Game>): EmptyResult<DataError> {
        return when (val result = localDataSource.upsertGames(games)) {
            is Result.Success -> result.asEmptyDataResult()
            is Result.Error -> Result.Error(result.error)
        }
    }


    override suspend fun updateGameDetails(gameId: Int): Result<Unit, DataError> {
        val roomId = localDataSource.getGameById(gameId).first().roomId
        return when (val details = remoteDataSource.fetchGameDetails(gameId, roomId)) {
            is Result.Success -> {
                try {
                    localDataSource.updateGameDetails(details.data)
                    Result.Success(Unit)
                } catch (e: Exception) {
                    Result.Error(DataError.Local.UNKNOWN)
                }
            }

            is Result.Error -> {
                Result.Error(details.error)
            }
        }
    }

    override fun getGameById(gameId: Int): Flow<Game> {
        return localDataSource.getGameById(gameId)
    }
}