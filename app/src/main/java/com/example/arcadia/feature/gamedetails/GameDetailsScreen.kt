package com.example.arcadia.feature.gamedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.example.arcadia.app.navigation.chrome.ChromeSpec
import com.example.arcadia.app.navigation.chrome.ContentInsetSpec
import com.example.arcadia.app.navigation.chrome.LocalChromeController
import com.example.arcadia.core.presentation.designsystem.component.PlaceholderImage
import com.example.arcadia.core.presentation.designsystem.component.chip.ChipGroup
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme
import com.example.arcadia.feature.gamedetails.components.CoverImage
import com.example.arcadia.feature.gamedetails.components.DescriptionText
import com.example.arcadia.feature.gamedetails.components.EdgeToEdgeSpacer
import com.example.arcadia.feature.gamedetails.components.RatingBar
import com.example.arcadia.feature.gamedetails.components.ScreenshotImageCarousel

@Composable
fun GameDetailsScreenRoot(
    gameId: Int,
    viewmodel: GameDetailsViewModel = hiltViewModel<GameDetailsViewModel, GameDetailsViewModel.Factory>(
        creationCallback = { factory -> factory.create(gameId) }
    )
) {
    val chrome = LocalChromeController.current
    chrome.override(
        ChromeSpec(insets = ContentInsetSpec(applyVerticalInset = false))
    )
    val game by viewmodel.uiState.collectAsStateWithLifecycle()
    GameDetailsScreen(
        game,
        onAction = { action ->
            viewmodel.onAction(action)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsScreen(
    state: GameDetailsUiState = GameDetailsUiState(),
    onAction: (GameDetailsActions) -> Unit = {}
) {

    state.game?.let { game ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            game.backgroundImage?.let { imageBackground ->
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.1f),
                    model = imageBackground,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    error = {
                        PlaceholderImage()
                    }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .navigationBarsPadding()
                    .verticalScroll(rememberScrollState())
            ) {
                EdgeToEdgeSpacer()
                CoverImage(game = game)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                        .height(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    RatingBar(rating = game.rating)
                }

                ChipGroup(
                    tag = game.genres,
                    horizontalAlignment = Alignment.CenterHorizontally
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    text = game.name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )

                if (state.error == null || state.game.description.isNotEmpty()) {
                    DescriptionText(
                        description = AnnotatedString.fromHtml(game.description),
                        expanded = state.descriptionExpanded,
                        onAction = onAction
                    )
                }

                ScreenshotImageCarousel(
                    modifier = Modifier.padding(vertical = 24.dp),
                    imageUrls = game.screenshots
                )
            }
        }
    }

}

@Preview
@Composable
private fun GameDetailsScreenPreview() {
    ArcadiaTheme {
        GameDetailsScreen()
    }
}