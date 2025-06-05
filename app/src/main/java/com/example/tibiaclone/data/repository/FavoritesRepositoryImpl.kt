package com.example.tibiaclone.data.repository

import com.example.tibiaclone.data.local.PokemonDao
import com.example.tibiaclone.data.local.PokemonEntity
import com.example.tibiaclone.domain.model.Pokemon
import com.example.tibiaclone.domain.repository.FavoritesRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: PokemonDao,
    private val gson: Gson
) : FavoritesRepository {

    override val favorites: Flow<List<Pokemon>> = dao.getAll().map { list ->
        list.map { entity -> gson.fromJson(entity.data, Pokemon::class.java) }
    }

    override suspend fun getPokemon(id: Int): Pokemon? {
        return dao.getById(id)?.let { gson.fromJson(it.data, Pokemon::class.java) }
    }

    override suspend fun addPokemon(pokemon: Pokemon) {
        val json = gson.toJson(pokemon)
        dao.insert(PokemonEntity(pokemon.id, json))
    }

    override suspend fun removePokemon(id: Int) {
        dao.deleteById(id)
    }
}
