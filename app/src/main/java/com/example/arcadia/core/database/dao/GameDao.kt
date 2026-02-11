package com.example.arcadia.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.arcadia.core.database.entity.GameEntity
import com.example.arcadia.core.database.util.DatabaseConstants
import com.example.arcadia.core.domain.model.GameDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Upsert
    suspend fun upsertAll(games: List<GameEntity>)

    @Query("SELECT * FROM ${DatabaseConstants.GAME_TABLE}")
    fun getPagedGames(): PagingSource<Int, GameEntity>

    @Query("DELETE FROM ${DatabaseConstants.GAME_TABLE}")
    suspend fun clearAll()

    @Update(entity = GameEntity::class)
    suspend fun updateGameDetails(gameDetails: GameDetails)

    @Query("SELECT * FROM ${DatabaseConstants.GAME_TABLE} WHERE id = :gameId")
    fun getGameById(gameId: Int): Flow<GameEntity>
}