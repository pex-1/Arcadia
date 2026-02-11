package com.example.arcadia.core.domain.model

import java.time.LocalDate

data class Game(
    val id: Int,
    val roomId: Int = 0,
    val name: String,
    val backgroundImage: String?,
    val rating: Float,
    val description: String = "",
    val parentPlatforms: List<PlatformType> = emptyList(),
    val genres: List<String> = emptyList(),
    val released: LocalDate? = null,
    val screenshots: List<String> = emptyList()
)