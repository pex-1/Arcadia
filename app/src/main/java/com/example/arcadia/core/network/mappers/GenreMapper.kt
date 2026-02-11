package com.example.arcadia.core.network.mappers

import com.example.arcadia.core.domain.model.Genre
import com.example.arcadia.core.network.genre.GenreDto

fun GenreDto.toDomain(): Genre =
    Genre(
        id = id,
        name = name,
        imageBackground = image_background,
        gameCount = games_count
    )