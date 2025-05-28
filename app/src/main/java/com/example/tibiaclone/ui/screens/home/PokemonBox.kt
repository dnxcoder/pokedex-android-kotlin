package com.example.tibiaclone.ui.screens.home
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tibiaclone.R
import com.example.tibiaclone.data.model.Pokemon


@Composable
fun PokemonBox(pokemon: Pokemon, onCLick: (Pokemon) -> Unit) {

    val configuration = LocalConfiguration.current;
    val screenWithDp = configuration.screenWidthDp.dp
    val boxWidth = screenWithDp.value * 0.38;

    val softBlush = colorResource(id = R.color.soft_blush);
    val borderGray = colorResource(id = R.color.border_gray);


    Column {
        Box(
            modifier = Modifier
                .width(boxWidth.dp)
                .height(150.dp)
                .padding(end = 5.dp)
                .background(softBlush)
                .border(width = 1.dp, color = borderGray, shape = RoundedCornerShape(8.dp))
                .padding(5.dp)
                .clickable {
                    onCLick(pokemon);
                }) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Boosted Creature", fontSize = 12.sp)
                AsyncImage(
                    model = pokemon.sprites.front_default,
                    contentDescription = "Sprite do $pokemon.name",
                    modifier = Modifier.weight(1f)
                )
                Text(pokemon.name, fontSize = 12.sp)
            }

        }
        Spacer(
            modifier = Modifier
                .height(5.dp)
                .fillMaxWidth()
        )
    }
}