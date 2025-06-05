package com.example.tibiaclone.domain.repository

import com.example.tibiaclone.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    /**
     * Flow containing the current list of favorite pokemons.
     */
    val favorites: Flow<List<Pokemon>>

    /**
     * Returns a pokemon from the favorites list or `null` if it's not present.
     */
    suspend fun getPokemon(id: Int): Pokemon?

    /**
     * Adds the given [pokemon] to the favorites list.
     */
    suspend fun addPokemon(pokemon: Pokemon)

    /**
     * Removes the pokemon with the provided [id] from the favorites list.
     */
    suspend fun removePokemon(id: Int)
}
