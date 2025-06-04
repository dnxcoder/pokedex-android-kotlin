package com.example.tibiaclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tibiaclone.ui.screens.details.DetailsScreen
import com.example.tibiaclone.ui.screens.favorites.FavoritesScreen

@Composable
fun FavoritesNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "favorites") {
        composable("favorites") {
            FavoritesScreen (onPokemonClick = { id ->
                navController.navigate("details/$id")
            })
        }
        composable("details/{pokemonId}") { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull()
            if (pokemonId != null) {
                DetailsScreen(pokemonId = pokemonId, navController = navController)
            }
        }
    }
}
