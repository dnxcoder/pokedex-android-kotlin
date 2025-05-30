package com.example.tibiaclone.ui.screens.details

import PokemonType
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.tibiaclone.data.model.Pokemon
import com.example.tibiaclone.utils.getPokemonBackgroundColor
import com.example.tibiaclone.utils.getPrettyRemoteSprites
import com.example.tibiaclone.viewmodel.DetailViewModel


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun DetailsScreen(pokemonId:Int, viewModel: DetailViewModel = hiltViewModel()) {
    val selectedPokemon by viewModel.selectedPokemon.collectAsState()

    Log.d("debug", "decription screen meu pokemon eh ${selectedPokemon!!.name}")

    selectedPokemon?.let { pokemon ->
        BoxWithConstraints {
            val spriteOffset = maxHeight * 0.1f

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(getPokemonBackgroundColor(pokemon))
            ) {
                TopSection(pokemon = pokemon, modifier = Modifier.align(Alignment.TopStart))
                BottomSection(modifier = Modifier.align(Alignment.BottomEnd))
                AsyncImage(
                    model = getPrettyRemoteSprites(pokemon.id),
                    contentDescription = "Sprite of ${pokemon.name}",
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .offset(y = -spriteOffset)
                        .zIndex(5f)
                )
            }
        }
    }
}

@Composable
fun TopSection(pokemon: Pokemon, modifier: Modifier) {
    Box(
        modifier = modifier
            .background(getPokemonBackgroundColor(pokemon))
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .zIndex(1f)
            .padding(top = 20.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .height(100.dp)
                    .padding(horizontal = 25.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = pokemon.name.replaceFirstChar { it.uppercase() },
                        fontSize = 40.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Row {
                        pokemon.types.forEach {
                            TypeBox(pokemon = pokemon, pokemonType = it.type.name)
                        }
                    }
                }

                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier
                        .weight(0.3f)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = formatToHashId(pokemon.id),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun BottomSection(modifier: Modifier) {
    val isAboutSelected = true
    val shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .clip(shape)
            .background(Color.White)
            .zIndex(1f)
            .padding(top = 80.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "About",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.conditionalBorder(isAboutSelected)
            )
            Text(
                text = "Base Stats",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.conditionalBorder(!isAboutSelected)
            )
        }
    }
}

@Composable
fun TypeBox(pokemon: Pokemon, pokemonType: PokemonType, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(20.dp)
    val backgroundColor = lerp(getPokemonBackgroundColor(pokemon), Color.White, 0.2f)

    Box(
        modifier = modifier
            .border(
                width = 2.dp, color = getPokemonBackgroundColor(pokemon), shape = shape
            )
            .clip(shape)
            .background(backgroundColor)
            .padding(10.dp)
    ) {
        Text(text = pokemonType.name, color = Color.White)
    }
}

fun formatToHashId(id: Int): String = "#${id.toString().padStart(3, '0')}"

fun Modifier.bottomBorder(strokeWidth: Dp, color: Color) = drawBehind {
    val strokePx = strokeWidth.toPx()
    drawLine(
        color = color,
        start = Offset(0f, size.height),
        end = Offset(size.width, size.height),
        strokeWidth = strokePx
    )
}

fun Modifier.conditionalBorder(condition: Boolean): Modifier =
    if (condition) this.then(Modifier.bottomBorder(2.dp, Color.Black)) else this
