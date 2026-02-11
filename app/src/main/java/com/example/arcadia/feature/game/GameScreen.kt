package com.example.arcadia.feature.game

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.presentation.designsystem.component.ArcadiaEmptyState
import com.example.arcadia.core.presentation.designsystem.component.StandardScreenScaffoldPadding
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme
import com.example.arcadia.feature.PreviewData
import com.example.arcadia.feature.game.components.ErrorListItem
import com.example.arcadia.feature.game.components.GameListItem
import com.example.arcadia.feature.game.components.GameListShimmer
import com.example.arcadia.feature.game.components.LoadingListItem
import kotlinx.coroutines.flow.flowOf

@Composable
fun GameScreenRoot(
    viewModel: GameViewModel = hiltViewModel(),
    onGameClick: (Int) -> Unit = {}
) {

    val pagedData = viewModel.pagedGames.collectAsLazyPagingItems()

    GameScreen(
        pagedData = pagedData,
        onAction = { action ->
            when (action) {
                is GameActions.OnGameClick -> onGameClick(action.gameId)
            }
        }
    )
}

@Composable
fun <T : Any> rememberPagedScreenState(items: LazyPagingItems<T>): PagedScreenState {
    val refresh = items.loadState.refresh
    val hasItems = items.itemCount > 0

    return when {
        refresh is LoadState.Loading || !hasItems && (refresh is LoadState.Error).not() ->
            PagedScreenState.FullscreenLoading

        refresh is LoadState.Error && !hasItems ->
            PagedScreenState.FullscreenError(refresh.error.message)

        else -> PagedScreenState.Content
    }
}

sealed interface PagedScreenState {
    data object FullscreenLoading : PagedScreenState
    data class FullscreenError(val message: String?) : PagedScreenState
    data object Content : PagedScreenState
}

@Composable
fun GameScreen(
    pagedData: LazyPagingItems<Game>,
    onAction: (GameActions) -> Unit = {}
) {

    StandardScreenScaffoldPadding {
        when (rememberPagedScreenState(pagedData)) {
            PagedScreenState.Content -> {
                Content(pagedData, onAction)
            }

            is PagedScreenState.FullscreenError -> {
                ArcadiaEmptyState(onRetryClick = {
                    pagedData.retry()
                })
            }

            PagedScreenState.FullscreenLoading -> {
                GameListShimmer()
            }
        }
    }

}

@Composable
fun Content(
    pagedData: LazyPagingItems<Game>,
    onAction: (GameActions) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(count = pagedData.itemCount) { index ->
            val item = pagedData[index]
            item?.let { game ->
                GameListItem(
                    game = game,
                    onClick = {
                        onAction(GameActions.OnGameClick(game.id))
                    })
            }
        }
        if (pagedData.loadState.append is LoadState.Error && pagedData.loadState.append.endOfPaginationReached.not()) {
            item {
                ErrorListItem {
                    pagedData.retry()
                }
            }
        } else if (pagedData.itemCount > 0 && pagedData.loadState.append.endOfPaginationReached.not()) {
            item {
                LoadingListItem()
            }
        }
    }
}

@Preview
@Composable
private fun GameScreenPreview() {
    ArcadiaTheme {
        GameScreen(pagedData = flowOf(PagingData.from(PreviewData.getGamesData())).collectAsLazyPagingItems())
    }
}