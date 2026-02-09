package com.example.springvalidation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.springvalidation.R
import com.example.springvalidation.ui.common_components.SpringValidationScaffold
import com.example.springvalidation.screens.components.SeasonCard
import com.example.springvalidation.screens.components.SeasonState
import com.example.springvalidation.screens.components.SpringModeToggle
import com.example.springvalidation.ui.theme.SpringValidationTheme
import kotlinx.coroutines.delay

/**
 * Season Status Indicator Screen.
 * Displays the current seasonal state with loading, winter, and spring states.
 */
@Composable
fun SeasonStatusScreen(modifier: Modifier = Modifier) {
    var currentState by remember { mutableStateOf(SeasonState.LOADING) }
    var isSpringModeEnabled by remember { mutableStateOf(false) }
    
    // Simulate system initialization
    LaunchedEffect(Unit) {
        delay(2000) // 2 second delay for loading
        currentState = SeasonState.WINTER
    }
    
    // Update state based on toggle when not loading
    LaunchedEffect(isSpringModeEnabled, currentState) {
        if (currentState != SeasonState.LOADING) {
            currentState = if (isSpringModeEnabled) SeasonState.SPRING else SeasonState.WINTER
        }
    }
    
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
            // Season card with status text
            SeasonCard(
                state = currentState,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Spring mode toggle at the bottom
            SpringModeToggle(
                checked = isSpringModeEnabled,
                onCheckedChange = { isSpringModeEnabled = it },
                enabled = currentState != SeasonState.LOADING,
                modifier = Modifier
                    .padding(bottom = 32.dp)
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
