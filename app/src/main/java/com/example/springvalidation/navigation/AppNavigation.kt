package com.example.springvalidation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.springvalidation.data.AppStateRepository
import com.example.springvalidation.ui.screens.FreshStartScreen
import com.example.springvalidation.ui.screens.StartScreen
import kotlinx.coroutines.launch

/**
 * Navigation routes for the app.
 */
object AppRoutes {
    const val START = "start"
    const val FRESH_START = "fresh_start"
}

/**
 * Main navigation graph for the Spring Validation app.
 * Handles navigation between Start Screen and Fresh Start Screen.
 * Uses AppStateRepository to determine initial destination based on saved state.
 */
@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val repository = AppStateRepository(context)
    val hasStartedNewMorning by repository.hasStartedNewMorning.collectAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    
    // Determine start destination based on saved state
    val startDestination = if (hasStartedNewMorning) {
        AppRoutes.FRESH_START
    } else {
        AppRoutes.START
    }
    
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppRoutes.START) {
            StartScreen(
                onStartNewMorning = {
                    // Save state before navigation
                    coroutineScope.launch {
                        repository.setNewMorningStarted()
                    }
                    navController.navigate(AppRoutes.FRESH_START)
                }
            )
        }
        
        composable(AppRoutes.FRESH_START) {
            FreshStartScreen()
        }
    }
}
