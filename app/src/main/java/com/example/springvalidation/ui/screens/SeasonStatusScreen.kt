package com.example.springvalidation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.ui.screens.components.SeasonCard
import com.example.springvalidation.ui.screens.components.SeasonState
import com.example.springvalidation.ui.theme.SpringValidationTheme

/**
 * Season Status Indicator Screen.
 * Displays the current seasonal state with loading, winter, and spring states.
 */
@Composable
fun SeasonStatusScreen(modifier: Modifier = Modifier) {
    SpringValidationScaffold(
        title = stringResource(R.string.season_status),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SeasonCard(
                state = SeasonState.LOADING,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SeasonStatusScreenPreview() {
    SpringValidationTheme {
        SeasonStatusScreen()
    }
}
