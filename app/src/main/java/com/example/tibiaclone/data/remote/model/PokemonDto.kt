package com.example.tibiaclone.data.remote.model

import com.example.tibiaclone.domain.model.PokemonType

data class PokemonDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: SpritesDto,
    val types: List<PokemonTypeSlotDto>,
    val stats: List<PokemonStatDto>,
    val base_experience: Int,
    val cries: CriesDto = CriesDto("", ""),
    val abilities: List<PokemonAbilityDto> = emptyList(),
    val gender_rate: Int = 0,
    val hatch_counter: Int = 0,
    val egg_groups: List<EggGroupDto> = emptyList()
)

data class CriesDto(
    val latest: String,
    val legacy: String
)

data class SpritesDto(
    val front_default: String
)

data class PokemonTypeSlotDto(
    val slot: Int,
    val type: TypeInfoDto
)

data class TypeInfoDto(
    val name: PokemonType
)

data class PokemonStatDto(
    val base_stat: Int,
    val stat: StatInfoDto
)

data class StatInfoDto(
    val name: String
)

data class PokemonAbilityDto(
    val ability: AbilityInfoDto,
    val is_hidden: Boolean,
    val slot: Int
)

data class AbilityInfoDto(
    val name: String,
    val url: String
)

data class EggGroupDto(
    val name: String,
    val url: String
)
