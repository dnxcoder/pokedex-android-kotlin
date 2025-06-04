package com.example.tibiaclone.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable



@Composable
fun MainScreenWithTabs() {
    val tabs = listOf(BottomNavItem.Home, BottomNavItem.Favorites)
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                tabs.forEach { tab ->
                    BottomNavigationItem(
                        selected = currentRoute == tab.route,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = tab.label) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) { HomeNavHost() }
            composable(BottomNavItem.Favorites.route) { FavoritesNavHost() }
        }
    }
}
