package com.example.arcadia.core.network.datasource

import com.example.arcadia.core.data.safeApiCall
import com.example.arcadia.core.network.mappers.toDomain
import com.example.arcadia.core.domain.datasource.RemoteGameDataSource
import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.model.GameDetails
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import com.example.arcadia.core.network.ArcadiaApi
import javax.inject.Inject

class RemoteGameDataSourceImpl @Inject constructor(
    private val api: ArcadiaApi
) : RemoteGameDataSource {
    override suspend fun fetchGames(
        page: Int,
        genreIds: List<String>
    ): Result<List<Game>, DataError.Network> {
        return safeApiCall {
            api.getGames(page, 20, genreIds.joinToString(",")).results.map { it.toDomain() }
        }
    }

    override suspend fun fetchGameDetails(
        gameId: Int,
        roomId: Int
    ): Result<GameDetails, DataError.Network> {
        return safeApiCall {
            api.getGameDetails(gameId).toDomain(roomId)
        }
    }
}