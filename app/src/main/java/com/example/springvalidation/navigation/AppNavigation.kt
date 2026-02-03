package com.example.springvalidation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.springvalidation.ui.screens.FreshStartScreen
import com.example.springvalidation.ui.screens.StartScreen

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
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = AppRoutes.START
    ) {
        composable(AppRoutes.START) {
            StartScreen(
                onStartNewMorning = {
                    navController.navigate(AppRoutes.FRESH_START)
                }
            )
        }
        
        composable(AppRoutes.FRESH_START) {
            FreshStartScreen()
        }
    }
}
