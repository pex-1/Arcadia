package com.example.arcadia.core.network.datasource

import com.example.arcadia.core.data.safeApiCall
import com.example.arcadia.core.domain.datasource.RemoteGenreDataSource
import com.example.arcadia.core.domain.model.Genre
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import com.example.arcadia.core.network.ArcadiaApi
import com.example.arcadia.core.network.mappers.toDomain
import javax.inject.Inject

class RemoteGenreDataSourceImpl @Inject constructor(
    private val api: ArcadiaApi
) : RemoteGenreDataSource {
    override suspend fun fetchGenres(): Result<List<Genre>, DataError.Network> {
        return safeApiCall {
            api.getGenres().results.map { it.toDomain() }
        }
    }
}