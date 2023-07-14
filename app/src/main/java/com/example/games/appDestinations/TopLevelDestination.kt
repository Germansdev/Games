package com.example.games.appDestinations


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector

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
        route = "STATISTICS",
        title = "Statistics",
        icon = Icons.Default.BarChart
    )

    object Pantalla5 : BottomBarScreen(
    route = "ShareScreen",
    title = "Shared",
    icon = Icons.Default.Share
    )
}
object Graph: NavigationDestination{
    const val BOTTOM = "bottomBar_graph"
    const val TOP = "topBar_graph"
    /**private*/ const val DETAILS =  "details_graph"
    private const val itemIdArg = "itemId"
    private val routeWithArgs = "$DETAILS/{$itemIdArg}"
    override val route: String
        get() = TODO("Not yet implemented")
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

enum class DetailsDestination (val route: String){

    DetailsScreen(route = "Details"),
    //MoreDetailsScreen(route = "More Details"),
   // MoreDeepScreen(route = "More Deep")

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


