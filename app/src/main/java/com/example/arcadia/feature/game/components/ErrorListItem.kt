package com.example.arcadia.feature.game.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arcadia.R
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme

@Composable
fun ErrorListItem(onTryClicked: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
    ) {
        Button(
            modifier = Modifier
                .wrapContentWidth()
                .align(Alignment.Center),
            shape = RoundedCornerShape(6.dp),
            onClick = onTryClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Preview
@Composable
private fun ErrorItem() {
    ArcadiaTheme {
        ErrorListItem {

        }
    }
}