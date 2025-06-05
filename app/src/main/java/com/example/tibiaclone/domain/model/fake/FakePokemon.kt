package com.example.tibiaclone.domain.model.fake

import com.example.tibiaclone.domain.model.Cries
import com.example.tibiaclone.domain.model.Pokemon
import com.example.tibiaclone.domain.model.PokemonType
import com.example.tibiaclone.domain.model.Stat

val fakePokemon = Pokemon(
    id = 1,
    name = "bulbasaur",
    height = 7,
    weight = 69,
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
    types = listOf(PokemonType.Grass, PokemonType.Poison),
    stats = listOf(
        Stat("hp", 45),
        Stat("attack", 49),
        Stat("defense", 49),
        Stat("special-attack", 65),
        Stat("special-defense", 65),
        Stat("speed", 45)
    ),
    baseExperience = 64,
    cries = Cries(latest = "", legacy = ""),
    abilities = listOf("overgrow", "chlorophyll"),
    genderRate = 1,
    hatchCounter = 20,
    eggGroups = listOf("monster", "grass")
)
