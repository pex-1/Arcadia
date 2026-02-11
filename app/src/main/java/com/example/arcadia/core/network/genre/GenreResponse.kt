package com.example.arcadia.core.network.genre

data class GenreResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GenreDto>
)