package com.example.arcadia.core.domain.datasource

import com.example.arcadia.core.domain.model.Genre
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import kotlinx.coroutines.flow.Flow

typealias GenreId = Int

interface LocalGenreDataSource {
    fun getGenres(): Flow<List<Genre>>
    suspend fun getSelectedGenreIds(): List<Int>
    suspend fun upsertGenres(genres: List<Genre>): Result<List<GenreId>, DataError.Local>
    suspend fun deleteAllGenres()
}