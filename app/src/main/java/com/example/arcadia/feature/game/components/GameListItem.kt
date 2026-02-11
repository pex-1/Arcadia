package com.example.arcadia.feature.game.components

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.arcadia.core.domain.model.Game
import com.example.arcadia.core.domain.util.simpleDateFormat
import com.example.arcadia.core.presentation.designsystem.component.chip.Chip
import com.example.arcadia.core.presentation.designsystem.component.chip.ChipGroup
import com.example.arcadia.core.presentation.designsystem.theme.ArcadiaTheme
import com.example.arcadia.core.presentation.designsystem.theme.DateIcon
import com.example.arcadia.core.presentation.designsystem.theme.RatingStar
import com.example.arcadia.core.presentation.designsystem.theme.StarIcon
import com.example.arcadia.core.util.applyMarquee
import com.example.arcadia.feature.PreviewData

@Composable
fun GameListItem(
    modifier: Modifier = Modifier,
    game: Game,
    onClick: (Game) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                onClick(game)
            }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        GameImageCard(
            imageUrl = game.backgroundImage,
            modifier = Modifier
                .padding(top = 10.dp)
                .height(100.dp)
                .width(85.dp),
            aspectRatio = 85 / 100f
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                modifier = Modifier
                    .applyMarquee()
                    .padding(top = 10.dp),
                text = game.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Chip(
                text = "${game.rating}/5",
                textStyle = MaterialTheme.typography.labelMedium,
                icon = StarIcon,
                iconColor = RatingStar,
                contentDescription = "star icon",
                textColor = MaterialTheme.colorScheme.onSurfaceVariant
            )

            ChipGroup(tag = game.genres)
            game.released?.let { date ->
                Chip(
                    text = date.simpleDateFormat(),
                    textStyle = MaterialTheme.typography.labelSmall,
                    icon = DateIcon,
                    iconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    textColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    contentDescription = "date icon",
                    hasBackground = false
                )
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun GameListItemPreview() {
    ArcadiaTheme {
        GameListItem(
            game = PreviewData.getGameData(4, 3),
        ) {}
    }
}