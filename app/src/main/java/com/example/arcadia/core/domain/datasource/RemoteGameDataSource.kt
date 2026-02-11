package com.example.arcadia.core.domain.datasource

import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.model.GameDetails
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result

interface RemoteGameDataSource {
    suspend fun fetchGames(page: Int, genreIds: List<String>):
            Result<List<Game>, DataError.Network>

    suspend fun fetchGameDetails(gameId: Int, roomId: Int): Result<GameDetails, DataError.Network>
}