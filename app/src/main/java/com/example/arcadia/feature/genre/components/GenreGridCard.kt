package com.example.arcadia.feature.genre.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arcadia.core.domain.model.Genre
import com.example.arcadia.core.presentation.designsystem.component.ImageCard
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme
import com.example.arcadia.core.presentation.designsystem.theme.GenreLabel
import com.example.arcadia.core.presentation.designsystem.theme.roundedCornerShape
import com.example.arcadia.feature.PreviewData

@Composable
fun GenreGridCard(
    modifier: Modifier = Modifier,
    genreItem: Genre,
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .border(
                width = 2.dp,
                color = if (genreItem.isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent,
                shape = roundedCornerShape.small,
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (genreItem.isSelected) 6.dp else 1.dp
        ),
        shape = roundedCornerShape.small,
    ) {

        Box {
            ImageCard(
                modifier = Modifier.fillMaxSize(),
                imageUrl = genreItem.imageBackground
            )

            if (genreItem.isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(10.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            0.5f to Color.Transparent,
                            1f to Color.Black.copy(alpha = 0.9f)
                        )
                    )
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = genreItem.name,
                    style = GenreLabel,
                )
            }
        }
    }
}

@Preview
@Composable
private fun GenreGridCardPreview() {
    ArcadiaTheme {
        GenreGridCard(genreItem = PreviewData.getGenreData().first())
    }
}