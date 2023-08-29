package com.example.games.appDestinations


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Games
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.games.R

// NAVIGATION BAR (BOTTOM NAVIGATION)
/**
data class BottomNavigationItem(

    val tittle: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null,
)*/

sealed class BottomBarScreen(
    val titleTextId: Int,
    val route: String,
    var title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    var hasNews: Boolean,
    var badgeCount: Int? = null,

    ) {
    object Pantalla1 : BottomBarScreen(
        titleTextId = R.string.app_name,
        route = "HOME-SCREEN",//
        title = "Home",//
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        hasNews = false,
        badgeCount = 0
    )

    object Pantalla2 : BottomBarScreen(
        titleTextId = R.string.favorites,
        route = "FAVORITES",
        title = "Favorites",
        unselectedIcon = Icons.Outlined.FavoriteBorder ,
        selectedIcon = Icons.Filled.Favorite,
        hasNews = false,
        badgeCount = 0
    )

    object Pantalla3 : BottomBarScreen(
        titleTextId = R.string.played,
        route = "PLAYED",
        title = "Played",
        unselectedIcon = Icons.Outlined.Games,
        selectedIcon = Icons.Filled.Games,
        hasNews = false,
        badgeCount = 0
    )

    object Pantalla4 : BottomBarScreen(
        titleTextId = R.string.statistics,
        route = "STATISTICS",
        title = "Statistics",
        unselectedIcon = Icons.Outlined.BarChart,
        selectedIcon = Icons.Filled.BarChart,
        hasNews = false,
        badgeCount = 0,
    )

    object Pantalla5 : BottomBarScreen(
        titleTextId = R.string.shared,
        route = "ShareScreen",
        title = "Shared",
        unselectedIcon = Icons.Outlined.Share,
        selectedIcon = Icons.Filled.Share,//Icons.Filled.Share,
        hasNews = false ,
        badgeCount = 0
    )
}

object Graph : NavigationDestination {
    const val BOTTOM = "bottomBar_graph"
    override val route: String
        get() = TODO("Not yet implemented")
    override val titleRes: Int
        get() = TODO("Not yet implemented")
}
/**
enum class DetailsDestination(val route: String) {
    DetailsScreen(route = "Details")
}*/

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


