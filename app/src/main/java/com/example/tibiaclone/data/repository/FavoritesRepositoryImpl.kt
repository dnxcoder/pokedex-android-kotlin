package com.example.tibiaclone.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tibiaclone.domain.model.Pokemon
import com.example.tibiaclone.domain.repository.FavoritesRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "favorites_prefs")

class FavoritesRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : FavoritesRepository {

    private object Keys {
        val FAVORITES = stringPreferencesKey("favorites_list")
    }

    private val gson = Gson()
    private val listType = object : TypeToken<List<Pokemon>>() {}.type

    override val favorites: Flow<List<Pokemon>> = context.dataStore.data.map { prefs ->
        prefs[Keys.FAVORITES]?.let { json ->
            gson.fromJson<List<Pokemon>>(json, listType)
        } ?: emptyList()
    }

    override suspend fun getPokemon(id: Int): Pokemon? {
        return favorites.first().find { it.id == id }
    }

    override suspend fun addPokemon(pokemon: Pokemon) {
        context.dataStore.edit { prefs ->
            val current = prefs[Keys.FAVORITES]?.let { gson.fromJson<List<Pokemon>>(it, listType) } ?: emptyList()
            if (current.none { it.id == pokemon.id }) {
                prefs[Keys.FAVORITES] = gson.toJson(current + pokemon)
            }
        }
    }

    override suspend fun removePokemon(id: Int) {
        context.dataStore.edit { prefs ->
            val current = prefs[Keys.FAVORITES]?.let { gson.fromJson<List<Pokemon>>(it, listType) } ?: emptyList()
            prefs[Keys.FAVORITES] = gson.toJson(current.filter { it.id != id })
        }
    }
}
