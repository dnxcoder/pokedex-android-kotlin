package com.example.tibiaclone.ui.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tibiaclone.ui.screens.home.PokemonBox
import com.example.tibiaclone.ui.screens.home.Subtitle
import com.example.tibiaclone.ui.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(
    onPokemonClick: (Int) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val favorites by viewModel.favoritePokemons.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Subtitle("Favorites")
        Spacer(modifier = Modifier.height(20.dp))

        if (favorites.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No favorites yet")
            }
        } else {
            LazyColumn(modifier = Modifier.padding(end = 10.dp)) {
                items(favorites.chunked(2)) { rowItems ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        rowItems.forEach { pokemon ->
                            PokemonBox(
                                pokemon = pokemon,
                                onCLick = { onPokemonClick(pokemon.id) },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(2.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
