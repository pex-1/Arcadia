package com.example.arcadia.feature.game.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.arcadia.core.presentation.designsystem.theme.SettingsIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopBar(
    onGenreClick: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(
                text = "Discover",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(
                onClick = {
                    onGenreClick()
                }
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = SettingsIcon,
                    contentDescription = "Settings",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

    )
}