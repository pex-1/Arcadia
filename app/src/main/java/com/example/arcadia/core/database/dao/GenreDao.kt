package com.example.arcadia.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.arcadia.core.database.entity.GenreEntity
import com.example.arcadia.core.database.util.DatabaseConstants
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Upsert
    suspend fun upsertAll(genres: List<GenreEntity>)

    @Query("SELECT * FROM ${DatabaseConstants.GENRE_TABLE}")
    fun observeGenres(): Flow<List<GenreEntity>>

    @Query("SELECT id FROM ${DatabaseConstants.GENRE_TABLE} WHERE isSelected = 1")
    suspend fun getSelectedGenres(): List<Int>

    @Query("DELETE FROM ${DatabaseConstants.GENRE_TABLE}")
    suspend fun clearAll()
}