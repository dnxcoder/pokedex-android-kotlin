package com.example.tibiaclone.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home_root", Icons.Default.Home, "Home")
    object Favorites : BottomNavItem("favorites_root", Icons.Default.Favorite, "Favorites")
}