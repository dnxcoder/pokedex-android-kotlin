package com.example.tibiaclone.data.network

import android.util.Log
import com.example.tibiaclone.data.model.Pokemon
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val pokemonApi: PokemonApi) {

    private val pokemonCache = mutableMapOf<Int, Pokemon>();

    suspend fun getPokemon(id: Int): Pokemon {
        return this.pokemonApi.getPokemonById(id);
    }

    fun getPokemonFromCache(id: Int?): Pokemon? {
        return pokemonCache[id];
    }


    suspend fun getFirstFiveHundredPokemons(): List<Pokemon> {
        val list = mutableListOf<Pokemon>();
        for (i in 1..500) {
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

    suspend fun getFirst20Pokemons(): List<Pokemon> {
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
