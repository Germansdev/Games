package com.example.games.appDestinations


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.games.R
import com.example.games.appDestinations.BottomBarScreen.Pantalla1.badgeCount

// NAVIGATION BAR (BOTTOM NAVIGATION)

data class BottomNavigationItem(

    val tittle: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)

sealed class BottomBarScreen(
    val titleTextId: Int,
    val route: String,
    var title: String,
    val icon: ImageVector,
    val hasNews: Boolean,
    var badgeCount: Int? = null,

    ) {

    object Pantalla1 : BottomBarScreen(
        titleTextId = R.string.app_name,
        route = "HOME-SCREEN",//
        title = "Home",//
        icon = Icons.Filled.Home,
        hasNews = true,
        badgeCount = 0
    )


    object Pantalla2 : BottomBarScreen(
        titleTextId = R.string.favorites,
        route = "FAVORITES",
        title = "Favorites",
        icon = Icons.Default.Favorite,
        hasNews = false,
        badgeCount = 0
    )

    object Pantalla3 : BottomBarScreen(
        titleTextId = R.string.played,
        route = "PLAYED",
        title = "Played",
        icon = Icons.Default.Games,
        hasNews = false,
        badgeCount = 0
    )

    object Pantalla4 : BottomBarScreen(
        titleTextId = R.string.statistics,
        route = "STATISTICS",
        title = "Statistics",
        icon = Icons.Default.BarChart,
        hasNews = true,
        badgeCount = null,
    )

    object Pantalla5 : BottomBarScreen(
        titleTextId = R.string.shared,
        route = "ShareScreen",
        title = "Shared",
        icon = Icons.Default.Share,
        hasNews = false,
        badgeCount = 0
    )
}

object Graph : NavigationDestination {
    const val BOTTOM = "bottomBar_graph"
    const val TOP = "topBar_graph"
    /**private*/
    const val DETAILS = "details_graph"
    private const val itemIdArg = "itemId"
    private val routeWithArgs = "$DETAILS/{$itemIdArg}"
    override val route: String
        get() = TODO("Not yet implemented")
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}

enum class DetailsDestination(val route: String) {

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


