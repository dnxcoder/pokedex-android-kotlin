package com.example.tibiaclone.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.tibiaclone.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** Implementation of [FavoritesRepository] backed by Jetpack DataStore. */
class FavoritesRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : FavoritesRepository {

    private val favoritesKey = stringSetPreferencesKey("favorite_pokemon_ids")

    override val favoriteIds: Flow<Set<Int>> = dataStore.data.map { prefs ->
        prefs[favoritesKey]?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
    }

    override suspend fun toggleFavorite(pokemonId: Int) {
        dataStore.edit { prefs ->
            val current = prefs[favoritesKey]?.toMutableSet() ?: mutableSetOf()
            val idString = pokemonId.toString()
            if (current.contains(idString)) {
                current.remove(idString)
            } else {
                current.add(idString)
            }
            prefs[favoritesKey] = current
        }
    }

    override fun isFavorite(pokemonId: Int): Flow<Boolean> =
        favoriteIds.map { it.contains(pokemonId) }
}
