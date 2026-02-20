package com.example.arcadia.core.database.datasource

import android.database.sqlite.SQLiteFullException
import com.example.arcadia.core.database.dao.GenreDao
import com.example.arcadia.core.database.entity.GenreEntity
import com.example.arcadia.core.network.mappers.toDomain
import com.example.arcadia.core.database.mappers.toGenreEntity
import com.example.arcadia.core.domain.datasource.GenreId
import com.example.arcadia.core.domain.datasource.LocalGenreDataSource
import com.example.arcadia.core.domain.model.Genre
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomLocalGenreDataSource @Inject constructor(
    private val genreDao: GenreDao
) : LocalGenreDataSource {

    override fun getGenres(): Flow<List<Genre>> {
        return genreDao.observeGenres()
            .map { it.map(GenreEntity::toDomain) }
    }

    override suspend fun getSelectedGenreIds(): List<Int> {
        return genreDao.getSelectedGenres()
    }

    override suspend fun upsertGenres(genres: List<Genre>): Result<List<GenreId>, DataError.Local> {
        return try {
            val genreEntities = genres.map(Genre::toGenreEntity)
            genreDao.upsertAll(genreEntities)
            Result.Success(genreEntities.map { it.id })
        } catch (e: SQLiteFullException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteAllGenres() {
        genreDao.clearAll()
    }
}