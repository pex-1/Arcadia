package com.example.arcadia.feature.game

interface GameActions {
    data class OnGameClick(val gameId: Int) : GameActions
}