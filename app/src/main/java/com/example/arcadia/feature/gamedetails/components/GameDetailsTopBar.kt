package com.example.arcadia.feature.gamedetails.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.arcadia.R
import com.example.arcadia.core.presentation.designsystem.theme.BackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailsTopBar(
    onBackClick: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        title = {

        },
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        navigationIcon = {
                IconButton(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clip(CircleShape),
                    onClick = onBackClick
                ) {
                    Icon(
                        imageVector = BackIcon,
                        contentDescription = "Go back",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
        }
    )
}