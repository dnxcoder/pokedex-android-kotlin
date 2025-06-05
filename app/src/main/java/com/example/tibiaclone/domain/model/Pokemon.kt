package com.example.tibiaclone.domain.model

/**
 * Domain model used across the application. It contains only the data that the
 * UI actually needs and is decoupled from the network response structure.
 */

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val imageUrl: String,
    val types: List<PokemonType>,
    val stats: List<Stat>,
    val baseExperience: Int,
    val cries: Cries,
    val abilities: List<String>,
    val genderRate: Int,
    val hatchCounter: Int,
    val eggGroups: List<String>
)

data class Stat(
    val name: String,
    val value: Int
)

data class Cries(
    val latest: String,
    val legacy: String
)
