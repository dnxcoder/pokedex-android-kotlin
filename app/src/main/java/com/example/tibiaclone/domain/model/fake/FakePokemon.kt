package com.example.tibiaclone.domain.model.fake

import com.example.tibiaclone.domain.model.*

val fakePokemon = Pokemon(
    id = 1,
    name = "bulbasaur",
    height = 7,
    weight = 69,
    sprites = Sprites(
        front_default = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
    ),
    types = listOf(
        PokemonTypeSlot(
            slot = 1,
            type = TypeInfo(name = PokemonType.Grass)
        ),
        PokemonTypeSlot(
            slot = 2,
            type = TypeInfo(name = PokemonType.Poison)
        )
    ),
    stats = listOf(
        PokemonStat(base_stat = 45, stat = StatInfo("hp")),
        PokemonStat(base_stat = 49, stat = StatInfo("attack")),
        PokemonStat(base_stat = 49, stat = StatInfo("defense")),
        PokemonStat(base_stat = 65, stat = StatInfo("special-attack")),
        PokemonStat(base_stat = 65, stat = StatInfo("special-defense")),
        PokemonStat(base_stat = 45, stat = StatInfo("speed"))
    ),
    base_experience = 64,

    abilities = listOf(
        PokemonAbility(
            ability = AbilityInfo(
                name = "overgrow",
                url = "https://pokeapi.co/api/v2/ability/65/"
            ),
            is_hidden = false,
            slot = 1
        ),
        PokemonAbility(
            ability = AbilityInfo(
                name = "chlorophyll",
                url = "https://pokeapi.co/api/v2/ability/34/"
            ),
            is_hidden = true,
            slot = 3
        )
    ),

    gender_rate = 1, // 12.5% female, 87.5% male
    hatch_counter = 20, // (20 + 1) * 255 = 5355 steps to hatch
    egg_groups = listOf(
        EggGroup(name = "monster", url = "https://pokeapi.co/api/v2/egg-group/1/"),
        EggGroup(name = "grass", url = "https://pokeapi.co/api/v2/egg-group/7/")
    ),
    cries = Cries(latest = "lol", legacy = "lol")
)
