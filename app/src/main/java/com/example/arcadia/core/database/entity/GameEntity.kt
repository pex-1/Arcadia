package com.example.arcadia.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.arcadia.core.database.util.DatabaseConstants
import com.example.arcadia.core.domain.model.PlatformType
import java.time.LocalDate

@Entity(tableName = DatabaseConstants.GAME_TABLE)
data class GameEntity(
    //auto generated key used to keep the same order
    @PrimaryKey(autoGenerate = true)
    val roomId: Int,
    val id: Int,
    val name: String,
    val backgroundImage: String?,
    val description: String,
    val rating: Float,
    val parentPlatforms: List<PlatformType>,
    val genres: List<String>,
    val released: LocalDate?,
    val screenshots: List<String> = emptyList()
)
