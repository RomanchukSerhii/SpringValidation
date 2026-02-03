package com.example.springvalidation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.springvalidation.presentation.screens.new_morning.NewMorningScreen
import com.example.springvalidation.presentation.screens.start_screen.StartScreenRoot
import com.example.springvalidation.presentation.screens.start_screen.StartViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Navigation routes for the app.
 */
object AppRoutes {
    const val START = "start"
    const val NEW_MORNING = "new_morning"
}

/**
 * Main navigation graph for the Spring Validation app.
 * Handles navigation between Start Screen and Fresh Start Screen.
 */
@Composable
fun AppNavigation(startRoute: String) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startRoute
    ) {
        composable(AppRoutes.START) {
            val viewModel: StartViewModel = koinViewModel()
            StartScreenRoot(
                viewModel = viewModel,
                onStartNewMorning = {
                    navController.navigate(
                        "${AppRoutes.NEW_MORNING}?showSnackbar=true"
                    )
                }
            )
        }

        composable(
            route = "${AppRoutes.NEW_MORNING}?showSnackbar={showSnackbar}",
            arguments = listOf(
                navArgument("showSnackbar") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) { backStackEntry ->

            val showSnackbar =
                backStackEntry.arguments?.getBoolean("showSnackbar") == true

            NewMorningScreen(
                showSnackbar = showSnackbar
            )
        }
    }
}
