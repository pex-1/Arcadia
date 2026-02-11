package com.example.arcadia.core.presentation.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    cornerShape: CornerBasedShape = RoundedCornerShape(8.dp),
    aspectRatio: Float = 1f
) {
    Box(
        modifier = modifier
            .aspectRatio(aspectRatio)
            .clip(cornerShape)
    ) {
        SubcomposeAsyncImage(
            modifier = modifier
                .fillMaxSize()
                .aspectRatio(aspectRatio),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),

            contentScale = ContentScale.Crop,
            contentDescription = null,
            loading = {
                ProgressIndicator()
            },
            error = {
                PlaceholderImage()
            }
        )
    }
}
