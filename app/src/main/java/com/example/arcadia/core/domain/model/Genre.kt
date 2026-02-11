package com.example.arcadia.core.domain.model

data class Genre(
    val id: Int,
    val name: String,
    val imageBackground: String = "",
    val gameCount: Int = -1,
    val isSelected: Boolean = false
)
