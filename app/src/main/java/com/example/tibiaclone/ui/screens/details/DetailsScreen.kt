package com.example.tibiaclone.ui.screens.details

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import coil.compose.AsyncImage
import com.example.tibiaclone.utils.getPrettyRemoteSprites
import com.example.tibiaclone.viewmodel.HomeViewModel

@Composable
fun DetailsScreen(
    viewModel: HomeViewModel
) {


    val selectedPokemon by viewModel.selectedPokemon.collectAsState()

    Log.d("Debug", selectedPokemon!!.name)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = selectedPokemon!!.name, fontSize = 24.sp)
        AsyncImage(
            model = getPrettyRemoteSprites(selectedPokemon!!.id),
            contentDescription = "Sprite offf",
            modifier = Modifier.size(200.dp)
        )


    }
}