package com.example.arcadia.feature.gamedetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.arcadia.core.util.shimmer

@Composable
fun DescriptionTextShimmer(
    modifier: Modifier = Modifier,
    lineHeight: Int = 14,
    lineSpacing: Int = 10,
    cornerRadius: Int = 6,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        val widths = listOf(1.00f, 0.92f, 0.98f, 0.88f, 0.62f)

        widths.forEachIndexed { index, w ->
            Box(
                modifier = Modifier
                    .fillMaxWidth(w)
                    .height(lineHeight.dp)
                    .shimmer(shape = RoundedCornerShape(cornerRadius.dp))
            )
            if (index != widths.lastIndex) {
                Spacer(Modifier.height(lineSpacing.dp))
            }
        }

    }
}