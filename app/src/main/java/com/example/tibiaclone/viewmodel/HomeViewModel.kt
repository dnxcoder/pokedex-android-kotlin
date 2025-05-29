package com.example.tibiaclone.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tibiaclone.data.model.Pokemon
import com.example.tibiaclone.data.model.Sprites
import com.example.tibiaclone.data.model.fake.fakePokemon
import com.example.tibiaclone.data.network.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class HomeViewModel : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    open val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    private val _selectedPokemon = MutableStateFlow<Pokemon?>(fakePokemon)
    open val selectedPokemon: StateFlow<Pokemon?> = _selectedPokemon

    init {
        viewModelScope.launch {
            _pokemonList.value = PokemonRepository.getFirstTenPokemons()
            Log.d("teste", "passou por aqui")
        }
    }

    fun setSelectedPokemon(pokemon: Pokemon) {
        this._selectedPokemon.value = pokemon
    }
}