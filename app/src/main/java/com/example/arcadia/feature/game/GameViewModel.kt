package com.example.arcadia.feature.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.arcadia.core.data.repository.GamePagingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    repository: GamePagingRepository
) : ViewModel() {

    val pagedGames = repository.getPagedGames().cachedIn(viewModelScope)

}