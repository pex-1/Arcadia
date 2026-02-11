package com.example.arcadia.app.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.arcadia.app.main.AppStartState
import com.example.arcadia.app.main.MainViewModel
import com.example.arcadia.app.navigation.NavigationRoot
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.appStartState.value is AppStartState.Loading
            }
        }
        enableEdgeToEdge()

        setContent {
            val state by viewModel.appStartState.collectAsStateWithLifecycle()
            ArcadiaTheme {
                if (state != AppStartState.Loading) {
                    NavigationRoot(state)
                }
            }
        }
    }
}