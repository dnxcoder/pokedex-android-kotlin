package com.example.tibiaclone.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tibiaclone.data.model.Pokemon
import com.example.tibiaclone.data.model.fake.fakePokemon
import com.example.tibiaclone.data.network.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    private val _selectedPokemon = MutableStateFlow<Pokemon?>(fakePokemon)
    val selectedPokemon: StateFlow<Pokemon?> = _selectedPokemon

    init {
        viewModelScope.launch {
            _pokemonList.value = pokemonRepository.getListOfPokemons(20);
        }
    }

    fun setSelectedPokemon(pokemon: Pokemon) {
        Log.d("debug", "trocando de pokemon ===> ${pokemon.name}")
        _selectedPokemon.value = pokemon
    }
}
