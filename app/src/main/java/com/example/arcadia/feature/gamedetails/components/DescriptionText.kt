package com.example.arcadia.feature.gamedetails.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.arcadia.core.util.noRippleClickable
import com.example.arcadia.feature.gamedetails.GameDetailsActions


@Composable
fun DescriptionText(
    description: AnnotatedString,
    expanded: Boolean,
    onAction: (GameDetailsActions) -> Unit = {}
) {

    if (description.isEmpty()) {
        DescriptionTextShimmer(
            modifier = Modifier.padding(top = 20.dp)
        )
    } else {
        var hasOverflow by remember { mutableStateOf(false) }
        Text(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .padding(top = 20.dp)
                .width(IntrinsicSize.Max),
            text = description,
            overflow = TextOverflow.Ellipsis,
            maxLines = if (expanded) Int.MAX_VALUE else 5,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Justify,
            onTextLayout = { textLayoutResult ->
                //check if there is any overflow before the text is expanded
                if (!expanded) {
                    hasOverflow = textLayoutResult.hasVisualOverflow
                }
            }
        )

        if (hasOverflow) {
            Text(
                text = if (expanded) "Read less" else "Read more",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .noRippleClickable {
                        onAction(GameDetailsActions.OnReadMoreClick)
                    }
            )
        }

    }
}
