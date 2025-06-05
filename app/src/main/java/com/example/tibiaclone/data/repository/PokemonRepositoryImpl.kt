package com.example.tibiaclone.data.repository

import android.util.Log
import com.example.tibiaclone.domain.model.Pokemon
import com.example.tibiaclone.data.remote.api.PokemonApi
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val pokemonApi: PokemonApi) : com.example.tibiaclone.domain.repository.PokemonRepository {

    private val pokemonCache = mutableMapOf<Int, Pokemon>();

    override suspend fun getPokemon(id: Int): Pokemon {
        return this.pokemonApi.getPokemonById(id);
    }

    override fun getPokemonFromCache(id: Int?): Pokemon? {
        return pokemonCache[id];
    }


    override suspend fun getListOfPokemons(quantity:Int): List<Pokemon> {
        val list = mutableListOf<Pokemon>();
        for (i in 1..quantity) {
            try {
                val pokemon = this.pokemonApi.getPokemonById(i);
                list.add(pokemon)
            } catch (error: Exception) {
                error.printStackTrace()
                Log.d("teste", "DEU ERROR NO POKEMON REPOSITORY PAI")
                Log.d("teste", error.toString())
            }
        }
        //adding Pokemons to Cache
        list.forEach { pokemonCache[it.id] = it }
        return list;
    }

    override suspend fun getFirst20Pokemons(): List<Pokemon> {
        val list = mutableListOf<Pokemon>();
        for (i in 1..20) {
            try {
                val pokemon = this.pokemonApi.getPokemonById(i);
                list.add(pokemon)
            } catch (error: Exception) {
                error.printStackTrace()
                Log.d("teste", "DEU ERROR NO POKEMON REPOSITORY PAI")
                Log.d("teste", error.toString())
            }
        }
        //adding Pokemons to Cache
        list.forEach { pokemonCache[it.id] = it }
        return list;
    }
}
