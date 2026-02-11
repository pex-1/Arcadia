package com.example.arcadia.core.database.mappers

import com.example.arcadia.core.database.entity.GameEntity
import com.example.arcadia.core.domain.model.Game

fun GameEntity.toDomain(): Game {
    return Game(
        roomId = roomId,
        id = id,
        name = name,
        backgroundImage = backgroundImage,
        rating = rating,
        parentPlatforms = parentPlatforms,
        genres = genres,
        released = released,
        screenshots = screenshots,
        description = description
    )
}

fun Game.toGameEntity(): GameEntity {
    return GameEntity(
        roomId = roomId,
        id = id,
        name = name,
        backgroundImage = backgroundImage,
        rating = rating,
        parentPlatforms = parentPlatforms,
        genres = genres,
        released = released,
        screenshots = screenshots,
        description = description
    )
}