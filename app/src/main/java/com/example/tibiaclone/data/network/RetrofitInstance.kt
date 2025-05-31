package com.example.tibiaclone.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //This class will provide objects
@InstallIn(SingletonComponent::class)

object RetrofitInstance {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(pokemonApi: PokemonApi): PokemonRepository {
        return PokemonRepository(pokemonApi)
    }
}