package com.example.games.appDestinations


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.ui.graphics.vector.ImageVector

// NAVIGATION BAR (BOTTOM NAVIGATION)

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector

) {
    object Pantalla1 : BottomBarScreen(
        route = "HOME-SCREEN",
        title = "Home",
        icon = Icons.Filled.Home
    )
/**
    object Pantalla2 : BottomBarScreen(
        route = "LIST-SCREEN",
        title = "List",
        icon = Icons.Filled.Home
    )*/

    object Pantalla2 : BottomBarScreen(
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

    object Pantalla5 : BottomBarScreen(
    route = "ShareScreen",
    title = "Share",
    icon = Icons.Default.Share
    )

}


