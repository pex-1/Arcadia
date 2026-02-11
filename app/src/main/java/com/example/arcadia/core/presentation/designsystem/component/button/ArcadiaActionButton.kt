package com.example.arcadia.core.presentation.designsystem.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme
import com.example.arcadia.core.presentation.designsystem.theme.roundedCornerShape

@Composable
fun ArcadiaActionButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    roundedCorners: Boolean = true,
    isLoading: Boolean = false,
) {
    Button(
        onClick = onClick,
        enabled = enabled && !isLoading,
        shape = if (roundedCorners) RoundedCornerShape(12.dp) else RoundedCornerShape(0.dp),
        modifier = modifier.heightIn(min = 48.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}


@Preview
@Composable
fun ArcadiaActionButtonPreview() {
    ArcadiaTheme {
        ArcadiaActionButton(text = "CONTINUE", onClick = {})
    }
}