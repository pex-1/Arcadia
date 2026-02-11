package com.example.arcadia.feature.gamedetails.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.example.arcadia.core.presentation.designsystem.component.ImageCard
import com.example.arcadia.core.presentation.designsystem.theme.roundedCornerShape
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenshotImageCarousel(
    modifier: Modifier = Modifier,
    imageUrls: List<String>
) {

    val pagerState = rememberPagerState { imageUrls.size }

    Column(
        modifier
            .padding(top = 24.dp)
            .defaultMinSize(minHeight = 300.dp)
            .fillMaxWidth()
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = 10.dp,
            contentPadding = PaddingValues(horizontal = 30.dp)
        ) { page ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ImageCard(
                    modifier = Modifier

                        .graphicsLayer {
                            val pageOffset =
                                (pagerState.currentPage - page + pagerState.currentPageOffsetFraction).absoluteValue

                            lerp(
                                start = 75.dp,
                                stop = 100.dp,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleY = scale / 100.dp
                            }
                        },
                    imageUrl = imageUrls[page],
                    cornerShape = roundedCornerShape.extraSmall
                )
            }
        }

        PagerIndicator(
            pageCount = imageUrls.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )

    }

}