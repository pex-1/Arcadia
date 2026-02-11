package com.example.arcadia.feature.gamedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.arcadia.core.domain.repository.GameRepository
import com.example.arcadia.core.domain.util.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = GameDetailsViewModel.Factory::class)
class GameDetailsViewModel @AssistedInject constructor(
    private val repository: GameRepository,
    @Assisted private val gameId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameDetailsUiState())
    val uiState: StateFlow<GameDetailsUiState> = _uiState.asStateFlow()

    init {
        observeGameDetails()
        updateGameDescription()
    }

    fun onAction(action: GameDetailsActions) {
        when (action) {
            is GameDetailsActions.OnReadMoreClick -> {
                _uiState.update { it.copy(descriptionExpanded = !_uiState.value.descriptionExpanded) }
            }
        }
    }

    private fun observeGameDetails() {
        viewModelScope.launch {
            repository.getGameById(gameId).collectLatest {
                _uiState.value = _uiState.value.copy(game = it)
            }
        }
    }

    private fun updateGameDescription() {
        viewModelScope.launch {
            val result = repository.updateGameDetails(gameId)
            if (result is Result.Error) {
                _uiState.update { it.copy(error = result.error) }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(gameId: Int): GameDetailsViewModel
    }
}