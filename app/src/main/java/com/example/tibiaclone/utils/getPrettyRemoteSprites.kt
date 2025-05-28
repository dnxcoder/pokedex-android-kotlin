package com.example.tibiaclone.utils

fun getPrettyRemoteSprites(idPokemon: Int): String {

    val transformId = String.format("%03d", idPokemon);

    val newURL =
        "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/${transformId}.png"

    return newURL;
}