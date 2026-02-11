package com.example.arcadia.app.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.arcadia.app.navigation.chrome.ChromeController

@Composable
fun rememberArcadiaAppState(): ArcadiaAppState {
    val chromeController = remember { ChromeController() }
    return remember(chromeController) { ArcadiaAppState(chromeController) }
}