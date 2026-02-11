package com.example.arcadia.feature.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcadia.core.domain.repository.GenreRepository
import com.example.arcadia.core.domain.util.DataError
import com.example.arcadia.core.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GenreViewModel @Inject constructor(
    private val genreRepository: GenreRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(GenreUiState(isLoading = true))
    val uiState: StateFlow<GenreUiState> = _uiState.asStateFlow()

    init {
        observeGenres()
    }

    fun onAction(action: GenreActions) {
        when (action) {
            is GenreActions.OnRetryAction -> {
                _uiState.update { it.copy(isRetrying = true) }
                refreshGenres()
            }

            is GenreActions.OnGenreClick -> {
                toggleGenreSelection(action.genreId)
            }
            is GenreActions.OnContinueClick -> {
                viewModelScope.launch {
                    saveIdsAndNavigateNext()
                }
            }
        }
    }

    private fun saveIdsAndNavigateNext() {
        viewModelScope.launch {
            val ids = uiState.value.selectedIds
            runCatching { genreRepository.storeGenreIds(ids) }
                .onSuccess {  }
        }
    }

    private fun toggleGenreSelection(genreId: Int) {
        val newSet = _uiState.value.selectedIds.toMutableSet()
        if (newSet.contains(genreId)) {
            newSet.remove(genreId)
        } else {
            newSet.add(genreId)
        }
        _uiState.update { it.copy(selectedIds = newSet) }
    }

    private fun observeGenres() {
        viewModelScope.launch {
            val savedIds = genreRepository.getGenreIds().first()
            genreRepository.observeGenres()
                .distinctUntilChanged()
                .collectLatest { list ->
                    if (list.isEmpty()) {
                        refreshGenres()
                    }
                    _uiState.update { state ->
                        state.copy(
                            genres = list,
                            isLoading = false,
                            selectedIds = savedIds
                        )
                    }
                }
        }
    }

    private fun refreshGenres() {
        viewModelScope.launch {
            when (val result = genreRepository.refreshGenres()) {
                is Result.Success -> {
                    _uiState.update { it.copy(isRetrying = false, errorMessage = null) }
                }

                is Result.Error -> {
                    _uiState.update { it.copy(isRetrying = false, errorMessage = result.error.toUiMessage()) }
                }
            }
        }

    }
}

fun DataError.Network.toUiMessage(): String =
    when (this) {
        DataError.Network.NO_INTERNET -> "No internet connection"
        else -> ""
    }