package com.example.tibiaclone.data.model

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<PokemonTypeSlot>,
    val stats: List<PokemonStat>,
    val base_experience: Int
)

data class Sprites(
    val front_default: String
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String
)

data class PokemonStat(
    val base_stat: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String // "hp", "attack", "defense", etc.
)
