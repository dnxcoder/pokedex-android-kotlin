package com.example.tibiaclone.domain.repository

import com.example.tibiaclone.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemon(id: Int): Pokemon
    fun getPokemonFromCache(id: Int?): Pokemon?
    suspend fun getListOfPokemons(quantity: Int): List<Pokemon>
    suspend fun getFirst20Pokemons(): List<Pokemon>
}
