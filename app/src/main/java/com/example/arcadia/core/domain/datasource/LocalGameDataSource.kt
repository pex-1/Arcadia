package com.example.arcadia.core.domain.datasource

import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.model.GameDetails
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias GameId = Int

interface LocalGameDataSource {
    suspend fun deleteAllGames()

    suspend fun upsertGames(games: List<Game>): Result<List<GameId>, DataError.Local>
    suspend fun updateGameDetails(gameDetails: GameDetails)

    fun getGameById(gameId: Int): Flow<Game>
}