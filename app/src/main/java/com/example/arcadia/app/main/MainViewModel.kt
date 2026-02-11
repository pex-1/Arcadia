package com.example.arcadia.app.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcadia.app.main.AppStartState
import com.example.arcadia.core.domain.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    settingsDataStore: SettingsDataStore
) : ViewModel() {

    val appStartState: StateFlow<AppStartState> = settingsDataStore.hasCompletedOnboarding
        .map { completed ->
            if (completed) {
                AppStartState.Home
            } else {
                AppStartState.Onboarding
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Companion.Eagerly,
            AppStartState.Loading
        )
}