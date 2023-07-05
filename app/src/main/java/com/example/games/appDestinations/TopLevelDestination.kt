package com.example.games.appDestinations


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.games.R

// NAVIGATION BAR (BOTTOM NAVIGATION)

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector

) {

    object Pantalla1 : BottomBarScreen(
        route = "LIST-SCREEN",
        title = "Games",
        icon = Icons.Filled.Home
    )

 /**   object Pantalla1 : BottomBarScreen(
        route = "HOME-SCREEN",
        title = "Home",
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
        route = "NOTPLAYED",
        title = "Explore",
        icon = Icons.Default.Rocket
    )

    object Pantalla5 : BottomBarScreen(
    route = "ShareScreen",
    title = "Shared",
    icon = Icons.Default.Share
    )


}

enum class DetailsDestination (val title: String){
    GameListScreen(title = "GameList"),
    DetailsScreen(title = "Details")
}


/**
 * Interface to describe the navigation destinations for the app
 */
interface NavigationDestination {
    /** Unique name to define the path for a composable*/
    val route: String

    /** String resource id to that contains title to be displayed for the screen.
     */
    val titleRes: Int
}


