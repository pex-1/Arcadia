package com.example.arcadia.feature.genre

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.arcadia.R
import com.example.arcadia.app.navigation.chrome.ChromeSpec
import com.example.arcadia.app.navigation.chrome.LocalChromeController
import com.example.arcadia.app.navigation.chrome.TopBarSpec
import com.example.arcadia.core.presentation.designsystem.component.ArcadiaEmptyState
import com.example.arcadia.core.presentation.designsystem.component.ProgressIndicator
import com.example.arcadia.core.presentation.designsystem.component.StandardScreenScaffoldPadding
import com.example.arcadia.core.presentation.designsystem.component.button.ArcadiaActionButton
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme
import com.example.arcadia.core.util.noRippleClickable
import com.example.arcadia.feature.PreviewData
import com.example.arcadia.feature.genre.components.GenreGridCard

@Composable
fun GenreScreenRoot(
    viewModel: GenreViewModel = hiltViewModel(),
    onContinueClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val chrome = LocalChromeController.current

    LaunchedEffect(state.errorMessage) {
        if (state.errorMessage != null) {
            chrome.override(
                ChromeSpec(topBar = TopBarSpec(visible = false))
            )
        } else {
            chrome.clearOverride()
        }
    }

    GenreScreen(
        state = state,
        onAction = { action ->
            viewModel.onAction(action)
            if (action == GenreActions.OnContinueClick) {
                onContinueClick()
            }
        }
    )
}

@Composable
fun GenreScreen(
    state: GenreUiState = GenreUiState(),
    onAction: (GenreActions) -> Unit = {}
) {
    StandardScreenScaffoldPadding {
        when {
            state.showEmptyState -> {
                ArcadiaEmptyState(
                    isLoading = state.isRetrying,
                    onRetryClick = { onAction(GenreActions.OnRetryAction) }
                )
            }

            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    ProgressIndicator()
                }
            }

            else -> {
                GenreContent(state, onAction)
            }
        }
    }
}

@Composable
fun GenreContent(
    state: GenreUiState = GenreUiState(),
    onAction: (GenreActions) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "${state.selectedCount} selected",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        val gridState = rememberLazyGridState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = gridState,
            modifier = Modifier
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = state.genres, key = { it.id }) { genre ->
                val selected = state.selectedIds.contains(genre.id)
                GenreGridCard(
                    modifier = Modifier
                        .noRippleClickable {
                            onAction(GenreActions.OnGenreClick(genre.id))
                        },
                    genreItem = genre.copy(isSelected = selected)
                )
            }
        }

        ArcadiaActionButton(
            text = stringResource(R.string.continue_button),
            modifier = Modifier.padding(vertical = 12.dp),
            enabled = state.canContinue,
            onClick = { onAction(GenreActions.OnContinueClick) }
        )
    }
}

@Preview
@Composable
private fun GenreScreenPreview() {
    ArcadiaTheme {
        GenreScreen(state = GenreUiState(genres = PreviewData.getGenreData()))
    }
}