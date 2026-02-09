package com.example.springvalidation.ui.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * Base scaffold for all challenge screens.
 * Provides consistent TopBar and white rounded container styling across challenges.
 *
 * @param title The title displayed in the TopBar
 * @param modifier Optional modifier for the scaffold
 * @param snackbarHostState Optional SnackbarHostState for displaying snackbars
 * @param content The content to display inside the white rounded container.
 *                Receives PaddingValues to handle system bars (navigation bar, etc.)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpringValidationScaffold(
    title: String,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        snackbarHost = {
            snackbarHostState?.let { SnackbarHost(hostState = it) }
        },
        containerColor = MaterialTheme.colorScheme.tertiary
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            content(
                PaddingValues(bottom = innerPadding.calculateBottomPadding())
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChallengeScaffoldPreview() {
    SpringValidationTheme {
        SpringValidationScaffold(
            title = "New Chapter"
        ) { paddingValues ->
            Text(
                text = "Your content goes here",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
