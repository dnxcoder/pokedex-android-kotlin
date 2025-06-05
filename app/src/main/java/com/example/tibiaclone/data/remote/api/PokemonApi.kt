package com.example.tibiaclone.data.remote.api

import com.example.tibiaclone.domain.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path



interface PokemonApi {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id:Int): Pokemon

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name:String): Pokemon
}