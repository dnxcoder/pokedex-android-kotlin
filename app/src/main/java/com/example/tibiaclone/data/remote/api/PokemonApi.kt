package com.example.tibiaclone.data.remote.api

import com.example.tibiaclone.data.remote.model.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path



interface PokemonApi {
    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id:Int): PokemonDto

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name:String): PokemonDto
}