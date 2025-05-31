package com.example.tibiaclone.ui.screens.details

import PokemonType
import android.annotation.SuppressLint
import android.text.Layout
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.filled.Volcano
import androidx.compose.material.icons.outlined.VolumeUp
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
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.tibiaclone.data.model.Pokemon
import com.example.tibiaclone.utils.getPokemonBackgroundColor
import com.example.tibiaclone.utils.getPrettyRemoteSprites
import com.example.tibiaclone.viewmodel.DetailViewModel
import com.example.tibiaclone.utils.*;

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun DetailsScreen(
    pokemonId: Int, navController: NavHostController, viewModel: DetailViewModel = hiltViewModel()
) {
    val selectedPokemon by viewModel.selectedPokemon.collectAsState()
    val isAboutTabSelected by viewModel.isAboutTabSelected.collectAsState();


    selectedPokemon?.let { pokemon ->
        BoxWithConstraints {
            val spriteOffset = maxHeight * 0.1f

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(getPokemonBackgroundColor(pokemon))
            ) {
                TopSection(
                    pokemon = pokemon,
                    modifier = Modifier.align(Alignment.TopStart),
                    goBack = { navController.popBackStack() })
                BottomSection(
                    pokemon = pokemon,
                    isAboutTabSelected = isAboutTabSelected,
                    modifier = Modifier.align(Alignment.BottomEnd),
                    toggleTab = { viewModel.toggleTab() })

                Box(
                    modifier = Modifier
                        .fillMaxSize(.65f)
                        .align(Alignment.Center)
                        .offset(y = -spriteOffset)
                        .zIndex(5f)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.VolumeUp,
                        contentDescription = "Make pokemon cry",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(y = 90.dp)
                            .size(70.dp)
                            .clickable { viewModel.playCryFromPokemon() },
                        tint = Color.White,
                    )
                    AsyncImage(
                        model = getPrettyRemoteSprites(pokemon.id),
                        contentDescription = "Sprite of ${pokemon.name}",
                        modifier = Modifier.fillMaxSize()
                    )


                }

            }
        }
    }
}

@Composable
fun TopSection(pokemon: Pokemon, modifier: Modifier, goBack: () -> Unit) {
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
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            goBack()
                        })
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
fun BottomSection(
    pokemon: Pokemon, isAboutTabSelected: Boolean, modifier: Modifier, toggleTab: () -> Unit
) {
    val shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .clip(shape)
            .background(Color.White)
            .zIndex(1f)
            .padding(top = 35.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 40.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,

                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "About",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .conditionalBorder(isAboutTabSelected)
                        .clickable { toggleTab() })
                Text(
                    text = "Base Stats",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .conditionalBorder(!isAboutTabSelected)
                        .clickable { toggleTab() })


            }
            Spacer(Modifier.height(20.dp))
            Column {
                if (isAboutTabSelected) {
                    AboutSection(pokemon)
                } else {
                    StatsSection(pokemon)
                }
            }

        }
    }
}

@Composable
fun AboutSection(pokemon: Pokemon) {
    StatsLines("Species", pokemon.types[0].type.name.name)
    StatsLines(
        "Height",
        "${pokemon.height.toString()}''  (${convertFeetToMetersAndCentimeters(pokemon.height)}m) "
    )
    StatsLines(
        "Weight", "${pokemon.weight} lbs (${convertPoundsToKilograms(pokemon.weight)}kgs)"
    )
    //MultipleStatsLines("Abilities", pokemon)
    StatsLines(
        "Weight", pokemon.abilities[0].ability.name.replaceFirstChar { it.uppercase() })

    Spacer(Modifier.height(15.dp))


    Text("Breeding", fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(10.dp))
    StatsLines(
        "Gender", "♂ ${(8 - pokemon.gender_rate) * 12.5} ♀ ${pokemon.gender_rate * 12.5}%"
    )
    StatsLines("Eggs Groups", "Monster")
    StatsLines("Egg Cycle", (pokemon.height.toInt() * 0.4 * 10).toInt().toString())
}

@Composable
fun StatsSection(pokemon: Pokemon) {
    StatusGraphs(title = "HP", value = pokemon.stats[0].base_stat, barColor = Color(0xfffc6c6d))
    Spacer(modifier = Modifier.height(10.dp))


    StatusGraphs(title = "Attack", value = pokemon.stats[1].base_stat, barColor = Color(0xff5eb382))
    Spacer(modifier = Modifier.height(10.dp))
    StatusGraphs(
        title = "Defense", value = pokemon.stats[2].base_stat, barColor = Color(0xfffc6c6d)
    )
    Spacer(modifier = Modifier.height(10.dp))
    StatusGraphs(title = "Sp.Atk", value = pokemon.stats[3].base_stat, barColor = Color(0xff5eb382))
    Spacer(modifier = Modifier.height(10.dp))
    StatusGraphs(title = "Sp.Def", value = pokemon.stats[4].base_stat, barColor = Color(0xfffc6c6d))
    Spacer(modifier = Modifier.height(10.dp))
    StatusGraphs(title = "Speed", value = pokemon.stats[5].base_stat, barColor = Color(0xff5eb382))
    Spacer(modifier = Modifier.height(10.dp))
    //StatusGraphs(title = "Total", value = pokemon.stats[6].base_stat, barColor = Color(0xfffc6c6d))
    Spacer(Modifier.height(15.dp))


    Text("Type defenses", fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(10.dp))
    Text(
        "The effectiveness of each type on ${pokemon.name}",
        fontWeight = FontWeight.SemiBold,
        color = Color.Gray
    )
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

@Composable
fun StatsLines(title: String, result: String) {
    Row(
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.weight(2f)
        )
        Text(
            result,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.weight(3f)
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun StatusGraphs(title: String, value: Int, barColor: Color) {

    Row {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.weight(2f)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(6f)
        ) {
            Text(value.toString(), modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .background(Color(0xFFF9F9F9))
                    .weight(6f)
                    .border(
                        width = 0.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(16.dp) // ← borda arredondada
                    )
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Box(
                    modifier = Modifier
                        .height(10.dp)
                        .background(barColor)
                        .background(barColor)
                        //.width(40.dp)
                        .fillMaxWidth(value / 100f)
                ) {}
            }
        }
    }
}

@Composable
fun MultipleStatsLines(title: String, pokemon: Pokemon) {
    Row(
        modifier = Modifier.fillMaxWidth()

    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.weight(2f)
        )
        Row(modifier = Modifier.weight(3f)) {

            pokemon.abilities.forEachIndexed { index, ability ->

                if (index + 1 < pokemon.abilities.size) {
                    Text(
                        text = "${ability.ability.name.replaceFirstChar { it.uppercase() }}, ",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                } else {
                    Text(
                        text = "${ability.ability.name.replaceFirstChar { it.uppercase() }}",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                    )
                }
            }
        }

    }
    Spacer(modifier = Modifier.height(10.dp))
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
