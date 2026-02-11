package com.example.arcadia.feature.game.components

import androidx.compose.foundation.layout.aspectRatio
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
import com.example.arcadia.core.presentation.designsystem.component.PlaceholderImage
import com.example.arcadia.core.presentation.designsystem.component.ProgressIndicator

@Composable
fun GameImageCard(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    cornerShape: CornerBasedShape = RoundedCornerShape(8.dp),
    aspectRatio: Float = 1f
) {
    SubcomposeAsyncImage(
        modifier = modifier
            .clip(cornerShape)
            .aspectRatio(aspectRatio),
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
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
