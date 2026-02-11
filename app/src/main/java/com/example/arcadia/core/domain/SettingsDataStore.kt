package com.example.arcadia.core.domain

import kotlinx.coroutines.flow.Flow

interface SettingsDataStore {
    val selectedGenreIds: Flow<Set<Int>>
    val hasCompletedOnboarding: Flow<Boolean>

    suspend fun setGenreIds(ids: Set<Int>)
}
