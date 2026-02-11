package com.example.arcadia.core.network.game

data class GameDto(
    val id: Int,
    val name: String,
    val background_image: String?,
    val rating: Float,
    val parent_platforms: List<PlatformDto>,
    val genres: List<GenreDto>,
    val released: String?,
    val short_screenshots: List<ScreenshotDto>
)

data class GenreDto(
    val id: Int,
    val name: String,
    val slug: String
)

data class PlatformDto(
    val platform: PlatformChild
)

data class PlatformChild(
    val id: Int,
    val name: String
)

data class ScreenshotDto(
    val id: Int,
    val image: String
)