package com.example.tibiaclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import com.example.tibiaclone.ui.screens.home.HomeScreen
import com.example.tibiaclone.ui.screens.details.DetailsScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable(
            route = "details/{pokemonName}/{spriteUrl}",
            arguments = listOf(
                navArgument("pokemonName") { type = NavType.StringType },
                navArgument("spriteUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("pokemonName") ?: "?"
            val spriteUrl = backStackEntry.arguments?.getString("spriteUrl") ?: ""
            DetailsScreen(name, spriteUrl)
        }
    }
}
