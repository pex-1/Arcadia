package com.example.arcadia.feature.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.arcadia.core.presentation.designsystem.theme.roundedCornerShape
import com.example.arcadia.core.util.shimmer

@Composable
fun GameItemShimmer(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(112.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Image placeholder
        Box(
            modifier = Modifier
                .width(85.dp)
                .height(100.dp)
                .shimmer(shape = roundedCornerShape.small)
        )

        Spacer(Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Title line
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(16.dp)
                    .shimmer(shape = roundedCornerShape.extraSmall)
            )

            // Rating chip
            Box(
                modifier = Modifier
                    .width(72.dp)
                    .height(14.dp)
                    .shimmer(shape = roundedCornerShape.extraSmall)
            )

            // Genre chips row
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .width(56.dp)
                        .height(14.dp)
                        .shimmer(shape = roundedCornerShape.extraSmall)
                )
                Box(
                    modifier = Modifier
                        .width(64.dp)
                        .height(14.dp)
                        .shimmer(shape = roundedCornerShape.extraSmall)
                )
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(14.dp)
                        .shimmer(shape = roundedCornerShape.extraSmall)
                )
            }

            // Date chip
            Box(
                modifier = Modifier
                    .width(92.dp)
                    .height(14.dp)
                    .shimmer(shape = roundedCornerShape.extraSmall)
            )
        }
    }
}

@Composable
fun GameListShimmer(
    count: Int = 15
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(count) {
            GameItemShimmer()
        }
    }
}