package com.example.tibiaclone.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tibiaclone.domain.model.Pokemon
import com.example.tibiaclone.domain.repository.FavoritesRepository
import com.example.tibiaclone.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _favoritePokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val favoritePokemons: StateFlow<List<Pokemon>> = _favoritePokemons

    init {
        viewModelScope.launch {
            favoritesRepository.favoriteIds.collect { ids ->
                val pokemons = ids.mapNotNull { id ->
                    pokemonRepository.getPokemonFromCache(id) ?: runCatching {
                        pokemonRepository.getPokemon(id)
                    }.getOrNull()
                }
                _favoritePokemons.value = pokemons
            }
        }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch { favoritesRepository.toggleFavorite(id) }
    }

    fun isFavorite(id: Int): Flow<Boolean> = favoritesRepository.isFavorite(id)
}
