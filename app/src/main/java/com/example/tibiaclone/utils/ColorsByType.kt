package com.example.tibiaclone.utils

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.example.tibiaclone.data.model.Pokemon


fun getPokemonBackgroundColor(pokemon: Pokemon): Color {


    return when (pokemon.types[0].type.name) {
        PokemonType.Normal -> Color(0xFF9FA19F)
        PokemonType.Fire -> Color(0xFFE52828)
        PokemonType.Water -> Color(0xFF2A81EF)
        PokemonType.Electric -> Color(0xFFFAC001)
        PokemonType.Grass -> Color(0xFF3FA12A)
        PokemonType.Ice -> Color(0xFF3CCEF3)
        PokemonType.Fighting -> Color(0xFFFC7F00)
        PokemonType.Poison -> Color(0xFF9141CB)
        PokemonType.Ground -> Color(0xFF905120)
        PokemonType.Flying -> Color(0xFF6B839A)
        PokemonType.Psychic -> Color(0xFFEF4279)
        PokemonType.Bug -> Color(0xFF91A119)
        PokemonType.Rock -> Color(0xFFAFA981)
        PokemonType.Ghost -> Color(0xFF6F4170)
        PokemonType.Dragon -> Color(0xFF5060E1)
        PokemonType.Dark -> Color(0xFF624C4D)
        PokemonType.Steel -> Color(0xFF60A1B8)
        PokemonType.Fairy -> Color(0xFFEF71EF)
    }


}