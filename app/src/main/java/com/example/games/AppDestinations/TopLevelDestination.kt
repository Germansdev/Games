package com.example.games.AppDestinations


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.ui.graphics.vector.ImageVector

// NAVIGATION BAR (BOTTOM NAVIGATION)

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Pantalla2  : BottomBarScreen(
        route = "FAVORITES",
        title = "Favorites",
        icon = Icons.Default.Favorite
    )

    object Pantalla3 : BottomBarScreen(
        route = "PLAYED",
        title = "Played",
        icon = Icons.Default.Games
    )

    object Pantalla4 : BottomBarScreen(
        route = "RATED",
        title = "Rated",
        icon = Icons.Default.StarRate
    )
    /**
    object Pantalla5 : BottomBarScreen(
        route = "PRUEBA",
        title = "Prueba",
        icon = Icons.Default.Settings
    )
    */
    object Pantalla6 : BottomBarScreen(
        route = "HomeScreen",
        title = "Home",
        icon = Icons.Filled.Home
    )
    object Pantalla7 : BottomBarScreen(
        route = "LISTSCREEN",
        title = "List",
        icon = Icons.Filled.Home
    )
}

