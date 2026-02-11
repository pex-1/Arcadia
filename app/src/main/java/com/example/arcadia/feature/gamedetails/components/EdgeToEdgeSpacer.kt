package com.example.arcadia.feature.gamedetails.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun EdgeToEdgeSpacer(
    appBarHeight: Dp = 64.dp
) {
    val statusBar = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    Spacer(
        modifier = Modifier
            .height(statusBar + appBarHeight)
    )
}