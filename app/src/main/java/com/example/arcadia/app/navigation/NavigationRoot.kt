package com.example.arcadia.app.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.arcadia.app.main.AppStartState
import com.example.arcadia.app.navigation.chrome.ChromeSpec
import com.example.arcadia.app.navigation.chrome.LocalChromeController
import com.example.arcadia.app.navigation.chrome.TopBarSpec
import com.example.arcadia.feature.game.GameScreenRoot
import com.example.arcadia.feature.game.components.GameTopBar
import com.example.arcadia.feature.gamedetails.GameDetailsScreenRoot
import com.example.arcadia.feature.gamedetails.components.GameDetailsTopBar
import com.example.arcadia.feature.genre.GenreScreenRoot
import com.example.arcadia.feature.genre.components.GenreTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationRoot(appStartState: AppStartState) {

    val appState = rememberArcadiaAppState()

    val firstScreen =
        if (appStartState is AppStartState.Home) AppDestination.Game
        else AppDestination.Genre

    val backStack = rememberNavBackStack(firstScreen)
    val current = backStack.lastOrNull() as AppDestination?

    fun defaultChromeFor(destination: AppDestination?): ChromeSpec {
        return when (destination) {
            AppDestination.Genre -> ChromeSpec(
                topBar = TopBarSpec(
                    backEnabled = backStack.size > 1
                ),
            )

            AppDestination.Game -> ChromeSpec(
                topBar = TopBarSpec(
                    backEnabled = false
                ),
            )

            is AppDestination.GameDetails -> ChromeSpec(
                topBar = TopBarSpec(
                    backEnabled = true
                ),
            )

            null -> ChromeSpec(
                topBar = TopBarSpec(visible = false),
            )
        }
    }

    LaunchedEffect(current) {
        appState.chromeController.clearOverride()
    }

    val resolvedChrome = appState.chromeController.resolve(defaultChromeFor(current))

    CompositionLocalProvider(LocalChromeController provides appState.chromeController) {

        Scaffold(
            contentWindowInsets = WindowInsets(0.dp),
            topBar = {
                if (resolvedChrome.topBar.visible) {
                    when (current) {
                        is AppDestination.Genre -> {
                            GenreTopBar(
                                onBackClick = {
                                    backStack.removeLastOrNull()
                                },
                                backClickEnabled = resolvedChrome.topBar.backEnabled
                            )
                        }

                        is AppDestination.Game -> {
                            GameTopBar(
                                onGenreClick = {
                                    backStack.add(AppDestination.Genre)
                                }
                            )
                        }

                        is AppDestination.GameDetails -> {
                            GameDetailsTopBar(
                                onBackClick = {
                                    backStack.removeLastOrNull()
                                }
                            )
                        }

                        else -> {}
                    }
                }
            }
        ) { _ ->
            NavDisplay(
                modifier = Modifier
                    .fillMaxSize(),
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator(),
                ),
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<AppDestination.Genre> {
                        GenreScreenRoot(
                            onContinueClick = {
                                backStack.removeLastOrNull()
                                backStack.add(AppDestination.Game)
                            })
                    }
                    entry<AppDestination.Game> {
                        GameScreenRoot(onGameClick = {
                            backStack.add(AppDestination.GameDetails(it))
                        })
                    }

                    entry<AppDestination.GameDetails> { navEntry ->
                        val gameId = navEntry.gameId
                        GameDetailsScreenRoot(gameId = gameId)
                    }
                }
            )
        }
    }

}

private fun PaddingValues.withoutVerticalInset(layoutDirection: LayoutDirection): PaddingValues {
    return PaddingValues(
        top = 0.dp,
        bottom = 0.dp,
        start = calculateStartPadding(layoutDirection),
        end = calculateEndPadding(layoutDirection)
    )
}
