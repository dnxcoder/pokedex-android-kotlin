package com.example.tibiaclone.data.remote.model

import com.example.tibiaclone.domain.model.Cries
import com.example.tibiaclone.domain.model.Pokemon
import com.example.tibiaclone.domain.model.Stat

/**
 * Maps [PokemonDto] coming from the network into the domain [Pokemon] model.
 */
fun PokemonDto.toPokemon(): Pokemon = Pokemon(
    id = id,
    name = name,
    height = height,
    weight = weight,
    imageUrl = sprites.front_default,
    types = types.map { it.type.name },
    stats = stats.map { Stat(it.stat.name, it.base_stat) },
    baseExperience = base_experience,
    cries = Cries(latest = cries.latest, legacy = cries.legacy),
    abilities = abilities.map { it.ability.name },
    genderRate = gender_rate,
    hatchCounter = hatch_counter,
    eggGroups = egg_groups.map { it.name }
)
