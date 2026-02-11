package com.example.arcadia.core.database.mappers

import com.example.arcadia.core.domain.model.GameDetails
import com.example.arcadia.core.network.gamedetails.GameDetailsDto

fun GameDetailsDto.toDomain(roomId: Int): GameDetails {
    return GameDetails(
        roomId = roomId,
        description = description
    )
}