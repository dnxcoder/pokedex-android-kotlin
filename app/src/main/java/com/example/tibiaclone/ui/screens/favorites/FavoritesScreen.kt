package com.example.tibiaclone.ui.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tibiaclone.ui.screens.home.PokemonBox
import com.example.tibiaclone.ui.screens.home.Subtitle
import com.example.tibiaclone.ui.theme.SetStatusBarColor
import com.example.tibiaclone.ui.viewmodel.FavoritesViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    onPokemonClick: (Int) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    SetStatusBarColor(color = Color.White, darkIcons = true)

    val pokemonList by viewModel.pokemonList.collectAsState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val isFavoritesRoute = navBackStackEntry?.destination?.route == "favorites"
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val commonMargin = screenWidthDp.value * 0.05

    if (pokemonList.isNotEmpty()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Subtitle("Favorites")
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(modifier = Modifier.padding(end = 10.dp)) {
                item { Spacer(modifier = Modifier.width(commonMargin.dp)) }
                items(pokemonList) { pokemon ->
                    PokemonBox(
                        pokemon = pokemon,
                        onCLick = { onPokemonClick(pokemon.id) },
                        enabled = isFavoritesRoute,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
