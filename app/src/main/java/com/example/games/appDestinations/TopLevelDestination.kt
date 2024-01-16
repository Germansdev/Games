package com.example.games.appDestinations


import com.example.games.R
import com.example.games.ui.theme.GamesIcons
import com.example.games.ui.theme.Icon
import com.example.games.ui.theme.Icon.ImageVectorIcon

sealed class BottomBarScreen(
    val titleTextId: Int,
    val route: String,
    var iconTextId: Int,
    val unselectedIcon: Icon,
    val selectedIcon: Icon,
    //var hasNews: Boolean,
    var badgeCount: Int? = null,

    ) {
    object Pantalla1 : BottomBarScreen(
        titleTextId = R.string.app_name,
        route = "HOME-SCREEN",
        iconTextId = R.string.homescreen,
        unselectedIcon = ImageVectorIcon(GamesIcons.HomeBorder),
        selectedIcon = ImageVectorIcon(GamesIcons.Home),
        //hasNews = false,
        badgeCount = 0
    )

    object Pantalla2 : BottomBarScreen(
        titleTextId = R.string.favorites,
        route = "FAVORITES",
        iconTextId = R.string.favorite,
        unselectedIcon = ImageVectorIcon(GamesIcons.FavoritesBorder),
        selectedIcon = ImageVectorIcon(GamesIcons.Favorites),
        //hasNews = false,
        badgeCount = 0
    )

    object Pantalla3 : BottomBarScreen(
        titleTextId = R.string.played,
        route = "PLAYED",
        iconTextId =R.string.playedIc,
        unselectedIcon = ImageVectorIcon(GamesIcons.PlayBorder),
        selectedIcon = ImageVectorIcon(GamesIcons.Play),
        //hasNews = false,
        badgeCount = 0
    )

    object Pantalla4 : BottomBarScreen(
        titleTextId = R.string.statistics,
        route = "STATISTICS",
        iconTextId = R.string.statistics,
        unselectedIcon = ImageVectorIcon(GamesIcons.BarChartBorder),
        selectedIcon = ImageVectorIcon(GamesIcons.BarChart),
        //hasNews = false,
        badgeCount = 0,
    )

    object Pantalla5 : BottomBarScreen(
        titleTextId = R.string.shared,
        route = "ShareScreen",
        iconTextId = R.string.sharedIC,
        unselectedIcon = ImageVectorIcon(GamesIcons.ShareBorder),
        selectedIcon = ImageVectorIcon(GamesIcons.Share),
        //hasNews = false ,
        badgeCount = 0
    )
}

/**
 * Interface to describe the navigation destinations for the app when
 * details screen, search screen, genre selected not played & played screen
 */
interface NavigationDestination {
    /** Unique name to define the path for a composable*/
    val route: String

    /** String resource id to that contains title to be displayed for the screen.
     */
    val titleRes: Int

}


