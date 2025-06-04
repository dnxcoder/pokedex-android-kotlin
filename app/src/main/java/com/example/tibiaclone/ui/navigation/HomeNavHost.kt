package com.example.tibiaclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tibiaclone.ui.screens.details.DetailsScreen
import com.example.tibiaclone.ui.screens.home.HomeScreen

@Composable
fun HomeNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController);
        }
        composable("details/{pokemonId}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull()
            if (pokemonId != null) {
                DetailsScreen(pokemonId = pokemonId, navController = navController)
            }
        }
    }
}
