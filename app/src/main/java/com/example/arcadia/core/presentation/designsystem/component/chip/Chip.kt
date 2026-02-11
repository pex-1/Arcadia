package com.example.arcadia.core.presentation.designsystem.component.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme
import com.example.arcadia.core.presentation.designsystem.theme.DateIcon

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String,
    icon: ImageVector? = null,
    hasBackground: Boolean = false,
    iconColor: Color = MaterialTheme.colorScheme.onSecondary,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    textColor: Color = MaterialTheme.colorScheme.onSecondary,
    textStyle: TextStyle = MaterialTheme.typography.labelSmall,
    contentDescription: String? = null
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        color = if (hasBackground) backgroundColor else Color.Transparent,
    ) {
        Row(
            modifier = Modifier.padding(
                vertical = 3.dp,
                horizontal = if (hasBackground) 8.dp else 0.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            icon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = contentDescription,
                    tint = iconColor,
                    modifier = Modifier
                        .height(14.dp)
                )
            }
            Text(
                text = text,
                style = textStyle,
                color = textColor
            )

        }
    }
}

@Preview
@Composable
private fun BackgroundChipPreview() {
    ArcadiaTheme {
        Chip(text = "Chip", hasBackground = true)
    }
}

@Preview
@Composable
private fun NoBackgroundChipPreview() {
    ArcadiaTheme {
        Chip(text = "Chip", icon = DateIcon)
    }
}