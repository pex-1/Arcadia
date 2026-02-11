package com.example.arcadia.feature.gamedetails

import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.util.DataError

data class GameDetailsUiState(
    val descriptionExpanded: Boolean = false,
    val game: Game? = null,
    val error: DataError? = null
)
