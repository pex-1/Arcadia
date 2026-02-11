package com.example.arcadia.core.domain.repository

import androidx.paging.PagingData
import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.EmptyResult
import com.example.arcadia.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface GameRepository {
    suspend fun upsertGames(games: List<Game>): EmptyResult<DataError>

    suspend fun updateGameDetails(gameId: Int): Result<Unit, DataError>

    fun getGameById(gameId: Int): Flow<Game>

}