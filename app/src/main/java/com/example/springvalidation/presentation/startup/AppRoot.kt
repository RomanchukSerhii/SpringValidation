package com.example.springvalidation.presentation.startup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel
import com.example.springvalidation.navigation.AppNavigation
import com.example.springvalidation.navigation.AppRoutes
import com.example.springvalidation.ui.common_components.SpringValidationScaffold

@Composable
fun AppRoot() {
    val viewModel: AppStartViewModel = koinViewModel()

    val startDestination by viewModel.startDestination.collectAsState()

    when (startDestination) {
        AppStartViewModel.StartDestination.Loading -> {
            SpringValidationScaffold("") { }
        }

        AppStartViewModel.StartDestination.Start -> {
            AppNavigation(startRoute = AppRoutes.START)
        }

        AppStartViewModel.StartDestination.FreshStart -> {
            AppNavigation(startRoute = AppRoutes.NEW_MORNING)
        }
    }
}
