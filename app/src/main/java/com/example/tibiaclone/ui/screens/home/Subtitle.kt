package com.example.tibiaclone.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Subtitle(text: String) {

    val configuration = LocalConfiguration.current;
    val screenWithDp = configuration.screenWidthDp.dp
    val commonMargin = screenWithDp.value * 0.05;

    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = commonMargin.dp, end = commonMargin.dp)
        ) {
            Text(
                "$text", fontWeight = FontWeight.SemiBold, fontSize = 18.sp
            )
        }
    }
}