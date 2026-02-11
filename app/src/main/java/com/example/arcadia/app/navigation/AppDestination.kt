package com.example.arcadia.app.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppDestination : NavKey {

    @Serializable
    data object Genre : AppDestination

    @Serializable
    data object Game : AppDestination

    @Serializable
    data class GameDetails(val gameId: Int) : AppDestination

}