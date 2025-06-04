package com.example.tibiaclone.ui.theme

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun SetStatusBarColor(color: Color, darkIcons: Boolean) {
    val context = LocalContext.current
    val window = (context as? Activity)?.window ?: return

    LaunchedEffect(color, darkIcons) {
        window.statusBarColor = color.toArgb()

        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = darkIcons
    }
}