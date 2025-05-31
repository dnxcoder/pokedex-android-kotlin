package com.example.tibiaclone.data.model

import PokemonType

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: Sprites,
    val types: List<PokemonTypeSlot>,
    val stats: List<PokemonStat>,
    val base_experience: Int,
    val cries: Cries,

    // Abilities (e.g., Overgrow, Chlorophyll)
    val abilities: List<PokemonAbility>,

    // Additional species-related fields
    val gender_rate: Int,          // -1 = genderless, 0–8 = female rate in 1/8 steps
    val hatch_counter: Int,        // Number of cycles needed to hatch (×255 = steps)
    val egg_groups: List<EggGroup> // Breeding groups the Pokémon belongs to
)

data class Cries (
    val latest: String,
    val legacy: String
)

data class Sprites(
    val front_default: String
)

data class PokemonTypeSlot(
    val slot: Int, val type: TypeInfo
)

data class TypeInfo(
    val name: PokemonType
)

data class PokemonStat(
    val base_stat: Int, val stat: StatInfo
)

data class StatInfo(
    val name: String // Examples: "hp", "attack", "defense"
)

data class PokemonAbility(
    val ability: AbilityInfo, val is_hidden: Boolean, val slot: Int
)

data class AbilityInfo(
    val name: String, val url: String
)

data class EggGroup(
    val name: String, val url: String
)
