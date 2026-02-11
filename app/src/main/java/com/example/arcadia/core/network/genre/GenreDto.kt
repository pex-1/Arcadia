package com.example.arcadia.core.network.genre

data class GenreDto(
    val id: Int,
    val name: String,
    val slug: String,
    val games_count: Int,
    val image_background: String,
)