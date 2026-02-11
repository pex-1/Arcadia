package com.example.arcadia.core.data.repository

import com.example.arcadia.core.domain.SettingsDataStore
import com.example.arcadia.core.domain.repository.GenreRepository
import com.example.arcadia.core.domain.datasource.LocalGenreDataSource
import com.example.arcadia.core.domain.datasource.RemoteGenreDataSource
import com.example.arcadia.core.domain.model.Genre
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteGenreDataSource,
    private val localDataSource: LocalGenreDataSource,
    private val settingsDataStore: SettingsDataStore
) : GenreRepository {
    override fun observeGenres(): Flow<List<Genre>> = localDataSource.getGenres()

    override suspend fun refreshGenres(): Result<Unit, DataError.Network> {
        return when (val result = remoteDataSource.fetchGenres()) {
            is Result.Success -> {
                val entities = result.data

                localDataSource.deleteAllGenres()
                localDataSource.upsertGenres(entities)

                Result.Success(Unit)
            }

            is Result.Error -> Result.Error(result.error)
        }
    }

    override suspend fun storeGenreIds(ids: Set<Int>) {
        settingsDataStore.setGenreIds(ids)
    }

    override fun getGenreIds(): Flow<Set<Int>> {
        return settingsDataStore.selectedGenreIds
    }

}