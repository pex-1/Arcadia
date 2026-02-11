package com.example.arcadia.core.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.arcadia.R

@Composable
fun PlaceholderImage() {
    Image(
        painterResource(id = R.drawable.placeholder),
        contentDescription = "placeholder image",
        contentScale = ContentScale.Crop
    )
}