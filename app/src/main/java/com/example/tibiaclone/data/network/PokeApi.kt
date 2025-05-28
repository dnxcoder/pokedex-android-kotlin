package com.example.tibiaclone.data.network

import com.example.tibiaclone.data.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id:Int): Pokemon

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name:String): Pokemon
}