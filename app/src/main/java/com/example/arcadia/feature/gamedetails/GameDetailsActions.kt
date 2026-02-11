package com.example.arcadia.feature.gamedetails

sealed interface GameDetailsActions {
    data object OnReadMoreClick : GameDetailsActions
}