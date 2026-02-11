package com.example.arcadia.core.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

fun Modifier.applyMarquee(): Modifier {
    return this.basicMarquee(
        animationMode = MarqueeAnimationMode.Immediately,
        iterations = Int.MAX_VALUE,
        initialDelayMillis = 1000,
        velocity = 30.dp,
        spacing = MarqueeSpacing(40.dp)
    )
}

fun Modifier.shimmer(
    shape: Shape = RoundedCornerShape(12.dp),
    baseAlpha: Float = 0.12f,
    highlightAlpha: Float = 0.24f,
    durationMs: Int = 1000
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "shimmer")

    val translateAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMs, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerTranslate"
    )

    val base = Color.White.copy(alpha = baseAlpha)
    val highlight = Color.White.copy(alpha = highlightAlpha)

    val brush = Brush.linearGradient(
        colors = listOf(base, highlight, base),
        start = Offset(translateAnim - 1000f, translateAnim - 1000f),
        end = Offset(translateAnim, translateAnim)
    )

    clip(shape).background(brush)
}

inline fun Modifier.noRippleClickable(
    crossinline onClick: () -> Unit
): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}