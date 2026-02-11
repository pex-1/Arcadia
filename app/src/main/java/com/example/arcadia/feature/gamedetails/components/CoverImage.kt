package com.example.arcadia.feature.gamedetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflowScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.util.monthFormat
import com.example.arcadia.core.presentation.designsystem.component.PlaceholderImage
import com.example.arcadia.core.presentation.designsystem.theme.roundedCornerShape
import com.example.arcadia.feature.gamedetails.util.getPlatformImage

@Composable
fun CoverImage(
    modifier: Modifier = Modifier,
    game: Game
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth(0.7f)
                .clip(shape = roundedCornerShape.small)
                .align(Alignment.CenterHorizontally)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = game.backgroundImage,
                contentDescription = game.name,
                contentScale = ContentScale.Crop,
                error = {
                    PlaceholderImage()
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            ),
                            startY = 200f
                        )
                    )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(vertical = 12.dp, horizontal = 8.dp)
                    .height(28.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,

                ) {
                game.released?.let { date ->
                    Text(
                        text = date.monthFormat(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .clip(shape = roundedCornerShape.extraSmall)
                            .background(MaterialTheme.colorScheme.onSurfaceVariant)
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                    )
                }

                FlowRow(
                    maxLines = 1,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    game.parentPlatforms.toSet()
                        .forEach { platformType ->

                            val image = platformType.getPlatformImage()
                            if (image != null) {
                                Icon(
                                    imageVector = image,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                    contentDescription = platformType.name,
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp, vertical = 4.dp)
                                        .height(18.dp)
                                        .width(18.dp)
                                )
                            }
                        }
                }
            }
        }
    }
}