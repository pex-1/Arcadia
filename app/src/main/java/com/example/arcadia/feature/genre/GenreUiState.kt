package com.example.arcadia.feature.genre

import com.example.arcadia.core.domain.model.Genre

data class GenreUiState(
    val isLoading: Boolean = false,
    val isRetrying: Boolean = false,
    val errorMessage: String? = null,
    val genres: List<Genre> = emptyList(),
    val selectedIds: Set<Int> = emptySet()
) {
    val showEmptyState: Boolean get() = !isLoading && errorMessage != null && genres.isEmpty()
    val selectedCount: Int get() = selectedIds.count()
    val canContinue: Boolean get() = selectedCount >= 3
}