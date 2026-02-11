package com.example.arcadia.app.main

sealed interface AppStartState {
    data object Loading : AppStartState
    data object Onboarding : AppStartState
    data object Home : AppStartState
}