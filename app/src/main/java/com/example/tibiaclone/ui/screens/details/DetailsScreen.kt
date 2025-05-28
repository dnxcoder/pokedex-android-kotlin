package com.example.tibiaclone.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(name: String, spriteUrl: String) {


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = name, fontSize = 24.sp)
        AsyncImage(
            model = spriteUrl,
            contentDescription = "Sprite offff $name",
            modifier = Modifier.size(200.dp)
        )



    }
}