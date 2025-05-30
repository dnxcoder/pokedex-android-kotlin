package com.example.tibiaclone.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.tibiaclone.data.model.Pokemon
import com.example.tibiaclone.data.network.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository, savedStateHandle: SavedStateHandle
) : ViewModel() {

    //O SavedStateHandle é automaticamente preenchido com os parâmetros
    // de rota quando você usa hiltViewModel()
    // dentro de uma composable() com NavBackStackEntry.

    private val _selectedPokemon = MutableStateFlow<Pokemon?>(null);

    val selectedPokemon: StateFlow<Pokemon?> = _selectedPokemon;

    init {
        //getting id automatically from chosen pokemon
        val pokemonIdFromRoute = savedStateHandle.get<String>("pokemonId")?.toIntOrNull()

        if (pokemonIdFromRoute != null) {
            _selectedPokemon.value = repository.getPokemonFromCache(pokemonIdFromRoute)
        }

    }
}