package com.example.tibiaclone.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp


@Composable
fun Title(name: String) {

    val configuration = LocalConfiguration.current;
    val screenWithDp = configuration.screenWidthDp.dp
    val commonMargin = screenWithDp.value * 0.05;


    Box {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .padding(start = commonMargin.dp, end = commonMargin.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                name, color = Color.Black, fontSize = 22.sp
            );
        }
    }
}