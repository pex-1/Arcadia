package com.example.arcadia.core.domain.datasource

import com.example.arcadia.core.domain.model.Genre
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result

interface RemoteGenreDataSource {
    suspend fun fetchGenres(): Result<List<Genre>, DataError.Network>
}