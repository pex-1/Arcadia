package com.example.arcadia.feature.genre

sealed interface GenreActions {
    data object OnRetryAction: GenreActions
    data class OnGenreClick(val genreId: Int): GenreActions
    data object OnContinueClick: GenreActions
}
