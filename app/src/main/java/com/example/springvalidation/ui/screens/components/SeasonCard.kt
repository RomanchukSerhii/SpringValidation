package com.example.springvalidation.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * Season card states representing loading, winter, and spring modes.
 */
enum class SeasonState {
    LOADING,
    WINTER,
    SPRING
}

/**
 * A card component that displays different seasonal states.
 * Shows a loading indicator, winter image, or spring image based on current state.
 * Includes a status text below the card.
 *
 * @param state Current season state to display
 * @param modifier Optional modifier for the card
 */
@Composable
fun SeasonCard(
    state: SeasonState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Card content
        val cardModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .clip(RoundedCornerShape(16.dp))
        
        Box(
            modifier = if (state == SeasonState.LOADING) {
                cardModifier.border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
            } else {
                cardModifier
            },
            contentAlignment = Alignment.Center
        ) {
            when (state) {
                SeasonState.LOADING -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                SeasonState.WINTER -> {
                    Image(
                        painter = painterResource(R.drawable.winter),
                        contentDescription = stringResource(R.string.winter_scene),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                SeasonState.SPRING -> {
                    Image(
                        painter = painterResource(R.drawable.spring),
                        contentDescription = stringResource(R.string.spring_scene),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        
        // Status text
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = when (state) {
                SeasonState.LOADING -> stringResource(R.string.checking_season_status)
                SeasonState.WINTER -> stringResource(R.string.waiting_for_spring)
                SeasonState.SPRING -> stringResource(R.string.spring_is_here)
            },
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun SeasonCardLoadingPreview() {
    SpringValidationTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SeasonCard(state = SeasonState.LOADING)
        }
    }
}

@Preview(showBackground = true, name = "Winter State")
@Composable
fun SeasonCardWinterPreview() {
    SpringValidationTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SeasonCard(state = SeasonState.WINTER)
        }
    }
}

@Preview(showBackground = true, name = "Spring State")
@Composable
fun SeasonCardSpringPreview() {
    SpringValidationTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SeasonCard(state = SeasonState.SPRING)
        }
    }
}
