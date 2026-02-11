package com.example.arcadia.core.database.mappers

import com.example.arcadia.core.database.entity.GenreEntity
import com.example.arcadia.core.domain.model.Genre

fun Genre.toGenreEntity(): GenreEntity {
    return GenreEntity(
        id = id,
        name = name,
        gameCount = gameCount,
        imageBackground = imageBackground,
    )
}

fun GenreEntity.toDomain(): Genre {
    return Genre(
        id = id,
        name = name,
        gameCount = gameCount,
        imageBackground = imageBackground,
        isSelected = isSelected
    )
}