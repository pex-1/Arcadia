package com.example.arcadia.app.navigation.chrome


import androidx.compose.runtime.staticCompositionLocalOf

val LocalChromeController = staticCompositionLocalOf<ChromeController> {
    error("ChromeController not provided")
}