package com.example.arcadia.core.presentation.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arcadia.R
import com.example.arcadia.core.presentation.designsystem.component.button.ArcadiaActionButton
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme
import com.example.arcadia.core.presentation.designsystem.theme.BrokenControllerIcon

@Composable
fun ArcadiaEmptyState(
    buttonEnabled: Boolean = true,
    title: String = stringResource(R.string.no_data_found),
    subtitle: String = stringResource(R.string.unable_to_load_data),
    isLoading: Boolean = false,
    onRetryClick: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .height(70.dp),
                imageVector = BrokenControllerIcon,
                contentDescription = stringResource(R.string.broken_controller_icon),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                text = title,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }

        ArcadiaActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            enabled = buttonEnabled,
            text = stringResource(R.string.retry),
            onClick = onRetryClick,
            roundedCorners = false,
            isLoading = isLoading
        )
    }


}


@Preview
@Composable
private fun ArcadiaEmptyStatePreview() {
    ArcadiaTheme {
        ArcadiaEmptyState(onRetryClick = {})
    }
}