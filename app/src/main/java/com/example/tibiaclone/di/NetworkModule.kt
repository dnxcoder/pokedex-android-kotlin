package com.example.tibiaclone.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.example.tibiaclone.data.remote.api.PokemonApi
import com.example.tibiaclone.data.repository.PokemonRepositoryImpl
import com.example.tibiaclone.data.repository.FavoritesRepositoryImpl
import com.example.tibiaclone.domain.repository.FavoritesRepository
import com.example.tibiaclone.domain.repository.PokemonRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providePokemonRepository(pokemonApi: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApi)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile("favorites") }
        )

    @Provides
    @Singleton
    fun provideFavoritesRepository(dataStore: DataStore<Preferences>): FavoritesRepository {
        return FavoritesRepositoryImpl(dataStore)
    }
}