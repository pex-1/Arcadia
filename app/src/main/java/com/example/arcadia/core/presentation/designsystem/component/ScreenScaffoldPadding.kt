package com.example.arcadia.core.presentation.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private val AppBarHeight = 64.dp

@Composable
fun StandardScreenScaffoldPadding(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val statusBar = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = statusBar + AppBarHeight)
            .navigationBarsPadding()
    ) {
        content()
    }
}