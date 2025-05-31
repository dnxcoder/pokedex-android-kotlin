package com.example.tibiaclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavHostController
import com.example.tibiaclone.ui.screens.home.HomeScreen
import com.example.tibiaclone.ui.screens.details.DetailsScreen
import com.example.tibiaclone.viewmodel.HomeViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    //val sharedViewModel: HomeViewModel = viewModel() // <- uma única instância aqui!

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { backStackEntry ->

            HomeScreen(navController)
        }
        composable("details/{pokemonId}") { backStackEntry ->
            //DetailsScreen(viewModel = sharedViewModel)
            val pokemonId = backStackEntry.arguments?.getString("pokemonId")?.toIntOrNull()

            if (pokemonId != null) {
                DetailsScreen(pokemonId = pokemonId, navController = navController)
            }
        }
    }
}
