package com.example.arcadia.core.database.datasource

import android.database.sqlite.SQLiteFullException
import androidx.paging.ExperimentalPagingApi
import com.example.arcadia.core.database.dao.GameDao
import com.example.arcadia.core.database.entity.GameEntity
import com.example.arcadia.core.database.mappers.toDomain
import com.example.arcadia.core.network.mappers.toDomain
import com.example.arcadia.core.database.mappers.toGameEntity
import com.example.arcadia.core.domain.datasource.GameId
import com.example.arcadia.core.domain.datasource.LocalGameDataSource
import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.model.GameDetails
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RoomLocalGameDataSource @Inject constructor(
    private val gameDao: GameDao,
) : LocalGameDataSource {

    override suspend fun deleteAllGames() {
        gameDao.clearAll()
    }

    override suspend fun upsertGames(games: List<Game>): Result<List<GameId>, DataError.Local> {
        return try {
            val entities = games.map(Game::toGameEntity)
            gameDao.upsertAll(entities)
            Result.Success(entities.map { it.id })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun updateGameDetails(gameDetails: GameDetails) {
        gameDao.updateGameDetails(gameDetails)
    }

    override fun getGameById(gameId: Int): Flow<Game> {
        return gameDao.getGameById(gameId).map(GameEntity::toDomain)
    }
}