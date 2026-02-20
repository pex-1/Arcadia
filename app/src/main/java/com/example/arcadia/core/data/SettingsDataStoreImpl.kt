package com.example.arcadia.core.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.arcadia.core.domain.SettingsDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsDataStore {

    private companion object {
        private val Context.settingsDataStore by preferencesDataStore(
            name = "settings_datastore"
        )
    }

    private val genreIdsKey = stringSetPreferencesKey("genre_ids")

    override val selectedGenreIds: Flow<Set<Int>> =
        context.settingsDataStore.data.map { preferences ->
            preferences[genreIdsKey]?.map { it.toInt() }?.toSet() ?: emptySet()
        }

    override val hasCompletedOnboarding: Flow<Boolean>
        get() = selectedGenreIds.map { it.isNotEmpty() }

    override suspend fun setGenreIds(ids: Set<Int>) {
        context.settingsDataStore.edit { preferences ->
            preferences[genreIdsKey] = ids.map { it.toString() }.toSet()
        }
    }
}