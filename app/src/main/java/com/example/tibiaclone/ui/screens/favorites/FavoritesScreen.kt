package com.example.tibiaclone.ui.screens.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun FavoritesScreen(onPokemonClick: (Int) -> Unit) {
    Column {
        Text("Favorites")
        Button(onClick = { onPokemonClick(25) }) {
            Text("Go to Favorite: Pikachu")
        }
    }
}
