package com.example.arcadia.core.domain.repository

import com.example.arcadia.core.domain.model.Genre
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface GenreRepository {
    fun observeGenres(): Flow<List<Genre>>
    suspend fun refreshGenres(): Result<Unit, DataError.Network>

    suspend fun storeGenreIds(ids: Set<Int>)

    fun getGenreIds(): Flow<Set<Int>>
}