package com.example.games.appDestinations


import com.example.games.R
import com.example.games.ui.theme.GamesIcons
import com.example.games.ui.theme.Icon
import com.example.games.ui.theme.Icon.ImageVectorIcon


/**
 * Different type of navigation supported by app depending on size and state.
 */
enum class GamesNavigationType {
    BOTTOM_NAVIGATION, NAVIGATION_RAIL, PERMANENT_NAVIGATION_DRAWER
}

/**
 * Content shown depending on size and state of device.
 */
enum class GamesContentType {
    LIST_ONLY, LIST_AND_DETAIL
}



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
    var iconTextId: Int,// String,
    val unselectedIcon: Icon,// 29 09: ImageVector,// Icon //
    val selectedIcon: Icon,// 29 09: ImageVector,
    var hasNews: Boolean,
    var badgeCount: Int? = null,

    ) {
    object Pantalla1 : BottomBarScreen(
        titleTextId = R.string.app_name,
        route = "HOME-SCREEN",//
        iconTextId = R.string.homescreen,//"Home",//
        unselectedIcon = ImageVectorIcon(GamesIcons.HomeBorder),//Icons.Outlined.Home,
        selectedIcon = ImageVectorIcon(GamesIcons.Home),//Icons.Filled.Home,
        hasNews = false,
        badgeCount = 0
    )

    object Pantalla2 : BottomBarScreen(
        titleTextId = R.string.favorites,
        route = "FAVORITES",
        iconTextId = R.string.favorite,
        unselectedIcon = ImageVectorIcon(GamesIcons.FavoritesBorder),//Icons.Outlined.FavoriteBorder ,
        selectedIcon = ImageVectorIcon(GamesIcons.Favorites),//Icons.Filled.Favorite,
        hasNews = false,
        badgeCount = 0
    )

    object Pantalla3 : BottomBarScreen(
        titleTextId = R.string.played,
        route = "PLAYED",
        iconTextId =R.string.playedIc,
        unselectedIcon = ImageVectorIcon(GamesIcons.PlayBorder),//Icons.Outlined.Games,
        selectedIcon = ImageVectorIcon(GamesIcons.Play),//Icons.Filled.Games,
        hasNews = false,
        badgeCount = 0
    )

    object Pantalla4 : BottomBarScreen(
        titleTextId = R.string.statistics,
        route = "STATISTICS",
        iconTextId = R.string.statistics,//"Statistics",
        unselectedIcon = ImageVectorIcon(GamesIcons.BarChartBorder),//Icons.Outlined.BarChart,
        selectedIcon = ImageVectorIcon(GamesIcons.BarChart),//Icons.Filled.BarChart,
        hasNews = false,
        badgeCount = 0,
    )

    object Pantalla5 : BottomBarScreen(
        titleTextId = R.string.shared,
        route = "ShareScreen",
        iconTextId = R.string.sharedIC,
        unselectedIcon = ImageVectorIcon(GamesIcons.ShareBorder),//Icons.Outlined.Share,
        selectedIcon = ImageVectorIcon(GamesIcons.Share),//Icons.Filled.Share,//Icons.Filled.Share,
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


