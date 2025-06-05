package com.example.tibiaclone.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val data: String
)
