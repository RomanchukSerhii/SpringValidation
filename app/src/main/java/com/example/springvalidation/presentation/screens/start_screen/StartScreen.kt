package com.example.springvalidation.presentation.screens.start_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.common_components.PrimaryButton
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.ui.theme.SpringValidationTheme

@Composable
fun StartScreenRoot(
    viewModel: StartViewModel,
    onStartNewMorning: () -> Unit = {}
) {
    StartScreen(
        onStartNewMorning = {
            viewModel.onStartNewMorning()
            onStartNewMorning()
        }
    )
}

@Composable
fun StartScreen(
    onStartNewMorning: () -> Unit = {}
) {

    SpringValidationScaffold(
        title = stringResource(R.string.start_screen_title)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.start_screen_prompt),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                )

                PrimaryButton(
                    text = stringResource(R.string.start_screen_button),
                    onClick = onStartNewMorning,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun StartScreenPreview() {
    SpringValidationTheme {
        StartScreen()
    }
}
