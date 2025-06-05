package com.example.tibiaclone.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Repository responsible for persisting and exposing the list of favorited Pokémon ids.
 */
interface FavoritesRepository {
    /** Flow containing the ids of the currently favorited Pokémon. */
    val favoriteIds: Flow<Set<Int>>

    /** Toggle the favorite state for the given [pokemonId]. */
    suspend fun toggleFavorite(pokemonId: Int)

    /** Observe whether the given [pokemonId] is marked as favorite. */
    fun isFavorite(pokemonId: Int): Flow<Boolean>
}
