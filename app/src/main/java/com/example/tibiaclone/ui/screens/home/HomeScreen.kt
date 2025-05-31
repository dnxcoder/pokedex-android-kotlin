package com.example.tibiaclone.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.example.tibiaclone.viewmodel.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()
) {
    val pokemonList by viewModel.pokemonList.collectAsState()

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val commonMargin = screenWidthDp.value * 0.05


    if (pokemonList.isNotEmpty()) {
        Column {

            Spacer(modifier = Modifier.height(20.dp))
            Subtitle("Pokedex")
            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(modifier = Modifier.padding(end = 10.dp)) {
                item { Spacer(modifier = Modifier.width(commonMargin.dp)) }

                items(pokemonList.chunked(2)) { rowItems ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        rowItems.forEach { pokemon ->
                            PokemonBox(
                                pokemon = pokemon,
                                onCLick = { it
                                    viewModel.setSelectedPokemon(it);
                                    Log.d("Debug", it.toString())
                                    navController.navigate("details/${pokemon.id}");
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(2.dp)
                            )
                        }
                    }
                }
            }

//            Box(
//                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
//            ) {
//                selectedPokemon?.let {
//                    Box(
//                        modifier = Modifier.clickable {
//
//                            getPokemonBackgroundColor(it)
//
//                            val name = it.name
//                            val spriteUrl =
//                                java.net.URLEncoder.encode(it.sprites.front_default, "UTF-8")
//                            navController.navigate("details/$name/$spriteUrl")
//                        }) {
//                        AsyncImage(
//                            model = it.sprites.front_default,
//                            contentDescription = "Sprite of ${it.name}",
//                            modifier = Modifier.size(300.dp)
//                        )
//                    }
//                }
//            }
        }
    } else {
        Box(

            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

}