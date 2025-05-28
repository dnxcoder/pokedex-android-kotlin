package com.example.tibiaclone.data.network

import android.util.Log
import com.example.tibiaclone.data.model.Pokemon

object PokemonRepository {

    suspend fun getFirstTenPokemons(): List<Pokemon>{
        val list = mutableListOf<Pokemon>()
        for (i in 1..200){
            try {
                val pokemon = RetrofitInstance.api.getPokemonById(i)
                list.add(pokemon)
            } catch (e: Exception){
                e.printStackTrace()
                Log.d("teste", "DEU ERROR NO POKEMON REPOSITORY PAI")
                Log.d("teste", e.toString())
            }
        }
        return list;
    }
}
