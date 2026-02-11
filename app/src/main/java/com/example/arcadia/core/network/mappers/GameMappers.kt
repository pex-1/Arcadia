package com.example.arcadia.core.network.mappers

import com.example.arcadia.core.domain.mapper.fromRaw
import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.model.PlatformType
import com.example.arcadia.core.network.game.GameDto
import java.time.LocalDate

fun GameDto.toDomain(): Game {
    return Game(
        id = id,
        name = name,
        backgroundImage = background_image,
        rating = rating,
        parentPlatforms = parent_platforms.map { PlatformType.fromRaw(it.platform.name) },
        genres = genres.map { it.name },
        released = released?.let { LocalDate.parse(it) },
        screenshots = short_screenshots.map { it.image }
    )
}