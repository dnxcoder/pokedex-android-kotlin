package com.example.tibiaclone.data.model.fake
import com.example.tibiaclone.data.model.*

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
            type = TypeInfo(name = "grass")
        ),
        PokemonTypeSlot(
            slot = 2,
            type = TypeInfo(name = "poison")
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
    base_experience = 64
)
