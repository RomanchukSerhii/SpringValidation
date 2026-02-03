package com.example.springvalidation.presentation.screens.new_morning

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.presentation.design_system.common_components.SpringValidationScaffold
import com.example.springvalidation.presentation.design_system.theme.SpringValidationTheme

/**
 * Screen that displays a fresh morning start experience.
 * Shows an inspirational illustration with motivational text to encourage new beginnings.
 */
@Composable
fun NewMorningScreen(
    showSnackbar: Boolean
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage = stringResource(R.string.new_morning_snackbar_message)
    var snackbarShown by rememberSaveable {
        mutableStateOf(false)
    }
    
    // Show snackbar on first composition
    LaunchedEffect(showSnackbar) {
        if (showSnackbar && !snackbarShown) {
            snackbarHostState.showSnackbar(snackbarMessage)
        }
    }
    
    SpringValidationScaffold(
        title = stringResource(R.string.new_morning_screen_title),
        snackbarHostState = snackbarHostState
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
            ) {
                // Large illustrative image
                Image(
                    painter = painterResource(R.drawable.morning_illustration),
                    contentDescription = stringResource(R.string.new_morning_image_description),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                        .clip(RoundedCornerShape(140.dp)),
                    contentScale = ContentScale.Crop
                )
                
                // Headline and subheadline
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.new_morning_headline),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    
                    Text(
                        text = stringResource(R.string.new_morning_subheadline),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}

/**
 * Preview for the Fresh Start screen.
 * Displays the full screen experience with system UI visible.
 */
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewMorningScreenPreview() {
    SpringValidationTheme {
        NewMorningScreen(true)
    }
}
