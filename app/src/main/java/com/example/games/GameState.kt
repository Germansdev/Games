package com.example.games

import androidx.compose.runtime.Composable
import androidx.core.os.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.example.games.appDestinations.BottomBarScreen

//NOT IMPLEMENTED YET (DO WHEN USE WINDOWS SIZE CLASS TO ADAPTATIVE):
/**
class GameState(
    val navController: NavHostController
){

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: BottomBarScreen?
        @Composable get() = when (currentDestination?.route) {
            homeScreenRoute.toString() -> BottomBarScreen.Pantalla1
            favoritesScreenRoute.toString() -> BottomBarScreen.Pantalla2
            playedScreenRoute.toString() -> BottomBarScreen.Pantalla3
            ratedScreenRoute.toString() -> BottomBarScreen.Pantalla4
            sharedScreenRoute.toString() -> BottomBarScreen.Pantalla5
            else -> null
        }
    val topLevelDestinations: List<BottomBarScreen>
        get() {
            TODO()
        }
    fun navigateToTopLevelDestination(topLevelDestination: BottomBarScreen) {
        trace("Navigation: ${topLevelDestination.route}") {
            val topLevelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }

            when (topLevelDestination) {
               // homeScreenRoute -> navController.navigateToHomeScreen(topLevelNavOptions)
               // favoritesScreenRoute -> navController.navigateToFavoritesScreen(topLevelNavOptions)
                //playedScreenRoute -> navController.navigateToPlayedScreen(topLevelNavOptions)
               // ratedScreenRoute -> navController.navigateToRatedScreen(topLevelNavOptions)
                //sharedScreenRoute -> navController.navigateToSharedScreen(topLevelNavOptions)
                BottomBarScreen.Pantalla1 -> navController.navigateToHomeScreen(topLevelNavOptions)
                BottomBarScreen.Pantalla2 -> navController.navigateToFavoritesScreen(topLevelNavOptions)
                BottomBarScreen.Pantalla3 -> navController.navigateToPlayedScreen(topLevelNavOptions)
                BottomBarScreen.Pantalla4 -> navController.navigateToRatedScreen(topLevelNavOptions)
                BottomBarScreen.Pantalla5 -> navController.navigateToSharedScreen(topLevelNavOptions)
            }
        }
    }

    fun navigateToSearch() {
        navController.navigateToSearch()
    }
}*/