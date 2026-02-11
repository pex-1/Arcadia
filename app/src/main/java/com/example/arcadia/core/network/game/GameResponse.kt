package com.example.arcadia.core.network.game

data class GameResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GameDto>,
)