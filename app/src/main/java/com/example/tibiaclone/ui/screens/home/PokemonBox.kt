package com.example.tibiaclone.ui.screens.home

import com.example.tibiaclone.domain.model.PokemonType
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.tibiaclone.R
import com.example.tibiaclone.domain.model.Pokemon
import com.example.tibiaclone.utils.getPokemonBackgroundColor
import com.example.tibiaclone.utils.getPrettyRemoteSprites

@Composable
fun PokemonBox(
    pokemon: Pokemon, modifier: Modifier = Modifier, onCLick: (Pokemon) -> Unit
) {
    val borderGray = colorResource(id = R.color.border_gray)


    Box(
        modifier = modifier
            .padding(0.dp)
            .fillMaxWidth()
            .height(150.dp)
            .background(
                getPokemonBackgroundColor(pokemon), shape = RoundedCornerShape(8.dp)
            )
            .border(width = 1.dp, color = borderGray, shape = RoundedCornerShape(8.dp))
            .clipToBounds() // overflow -> hidden
            .clickable {
                onCLick(pokemon)
            }
            .padding(5.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
        ) {
            Text(
                pokemon.name.replaceFirstChar { it.uppercase() },
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.zIndex(2f)
            )
            pokemon.types.forEach {

                TypeBox(
                    pokemon = pokemon, pokemonType = it.type.name, modifier = Modifier
                        .align(Alignment.Start)
                        .offset(x = -(15.dp))
                )
            }
        }
        AsyncImage(
            model = getPrettyRemoteSprites(pokemon.id),
            contentDescription = "Sprite do ${pokemon.name}",
            modifier = Modifier
                .fillMaxSize(0.9f)
                .align(Alignment.BottomEnd)
                .offset(x = 25.dp, y = 0.dp)
                .zIndex(2f)
        )
        AsyncImage(
            model = "https://pokedex.dnxcoder.com/static/media/pokeball-logo.a3591748.png",
            contentDescription = "Sprite do ${pokemon.name}",
            modifier = Modifier
                .fillMaxSize(0.8f)
                .align(Alignment.CenterEnd)
                .offset(x = 25.dp, y = 0.dp)
                .zIndex(1f)
                .alpha(0.2f)
        )


    }


}

@Composable
fun TypeBox(pokemon: Pokemon, pokemonType: PokemonType, modifier: Modifier) {


    val shape = RoundedCornerShape(20.dp)

    Box(
        modifier = modifier

            .border(
                width = 2.dp,
                color = getPokemonBackgroundColor(pokemon),
                shape = RoundedCornerShape(20.dp)
            )
            .clip(shape) // cuts everything that comes after
            .background(lerp(getPokemonBackgroundColor(pokemon), Color.White, 0.2f))
            .padding(10.dp)


    ) {
        Text(text = pokemonType.name, color = Color.White)
    }
}
