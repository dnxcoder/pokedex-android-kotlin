package com.example.tibiaclone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.tibiaclone.data.remote.api.PokemonApi
import com.example.tibiaclone.data.repository.PokemonRepositoryImpl
import com.example.tibiaclone.data.repository.FavoritesRepositoryImpl
import com.example.tibiaclone.domain.repository.PokemonRepository
import com.example.tibiaclone.domain.repository.FavoritesRepository
import com.example.tibiaclone.data.local.PokemonDao
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module //This class will provide objects
@InstallIn(SingletonComponent::class)

object NetworkModule {

    @Provides
    @Singleton
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(PokemonApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun providePokemonRepository(pokemonApi: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApi)
    }

    @Provides
    @Singleton
    fun provideFavoritesRepository(
        dao: PokemonDao,
        gson: Gson
    ): FavoritesRepository {
        return FavoritesRepositoryImpl(dao, gson)
    }
}