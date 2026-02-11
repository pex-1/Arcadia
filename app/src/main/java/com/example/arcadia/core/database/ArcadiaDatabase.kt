package com.example.arcadia.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.arcadia.core.database.converters.ArcadiaTypeConverters
import com.example.arcadia.core.database.dao.GameDao
import com.example.arcadia.core.database.dao.GenreDao
import com.example.arcadia.core.database.entity.GameEntity
import com.example.arcadia.core.database.entity.GenreEntity

@Database(
    entities = [GameEntity::class, GenreEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(ArcadiaTypeConverters::class)
abstract class ArcadiaDatabase : RoomDatabase() {

    abstract val genreDao: GenreDao
    abstract val gameDao: GameDao

}