package com.example.tibiaclone.di

import android.content.Context
import androidx.room.Room
import com.example.tibiaclone.data.local.AppDatabase
import com.example.tibiaclone.data.local.PokemonDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "favorites.db").build()
    }

    @Provides
    fun providePokemonDao(db: AppDatabase): PokemonDao = db.pokemonDao()
}
