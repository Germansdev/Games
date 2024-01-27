package com.example.games

//import com.example.games.appDestinations.Graph

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.Pantalla1
import com.example.games.model.Game
import com.example.games.search.SearchScreen
import com.example.games.search.SearchScreenDestination
import com.example.games.search.navigateToSearch
import com.example.games.ui.CustomTopBar
import com.example.games.ui.GameViewModel
import com.example.games.ui.GamesState
import com.example.games.ui.screens.DetailsScreen
import com.example.games.ui.screens.FavoritesScreen
import com.example.games.ui.screens.GameListCategoryScreenDestination
import com.example.games.ui.screens.GameListCategoryScreenDestinationPlayed
import com.example.games.ui.screens.GamesListCategoryScreen
import com.example.games.ui.screens.GamesListCategoryScreenPlayed
import com.example.games.ui.screens.ItemDetailsDestination
import com.example.games.ui.screens.NotPlayedScreen
import com.example.games.ui.screens.Played
import com.example.games.ui.screens.SharedScreen
import com.example.games.ui.screens.Statistics
import com.example.games.ui.theme.GamesIcons


@SuppressLint("NewApi")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun GameNavHost(
    appState: GamesState,
    modifier: Modifier = Modifier,
    startDestination: String = Pantalla1.route,
    viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory),
) {

    val navController = appState.navController
    val viewModel: GameViewModel =
        viewModel(factory = GameViewModel.Factory)
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {

        composable(
            route = Pantalla1.route
        ) {

            NotPlayedScreen(

                onClick = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                },
            ) { genre ->
                navController.navigate("${GameListCategoryScreenDestination.route}/${genre}")
            }

        }

        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {

            DetailsScreen(
                gameDetails = Game(),//GameEntity(),
                onClick = { navController.popBackStack() },
                modifier,
                shouldShowGradientBackground = true,
            )
        }

        composable(BottomBarScreen.Pantalla2.route) {
            FavoritesScreen()
        }

        composable(BottomBarScreen.Pantalla3.route) {
            Played(
                onClick = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                },
                onGenreClick = { genre ->
                    navController.navigate("${GameListCategoryScreenDestinationPlayed.route}/${genre}")
                },
            )
        }

        composable(BottomBarScreen.Pantalla4.route) {
            Statistics()
        }

        composable(BottomBarScreen.Pantalla5.route) {
            SharedScreen()
        }

        composable(route = SearchScreenDestination.route) {
            CustomTopBar(
                titleRes = R.string.search,
                navigationIcon = GamesIcons.Search,
                navigationIconContentDescription = null,
                actionIcon = GamesIcons.Close,
                actionIconContentDescription = null,
                onNavigationClick = { navController.navigateToSearch() },
            )
        }

        composable(route = SearchScreenDestination.route) {
            SearchScreen(
                onClick = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = GameListCategoryScreenDestinationPlayed.routeWithArgs,
            arguments = listOf(navArgument(GameListCategoryScreenDestinationPlayed.itemIdArg)
            { type = NavType.StringType })
        ) { backStackEntry ->
            val gameGenre =
                backStackEntry.arguments?.getString(GameListCategoryScreenDestinationPlayed.itemIdArg)
            GamesListCategoryScreenPlayed(
                gameGenre = gameGenre ?: "",
                modifier = Modifier,
                onClick = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                },
                onBack = {
                    navController.popBackStack()
                    navController.navigate(BottomBarScreen.Pantalla3.route) {
                        launchSingleTop = false
                    }
                },
                onGenreClick = { genre ->
                    navController.navigate("${GameListCategoryScreenDestinationPlayed.route}/${genre}")
                },
            )
        }
        //NOT PLAYED:
        composable(
            route = GameListCategoryScreenDestination.routeWithArgs,
            arguments = listOf(navArgument(GameListCategoryScreenDestination.itemIdArg)
            { type = NavType.StringType })
        ) {

                backStackEntry ->
            val gameGenre =
                backStackEntry.arguments?.getString(GameListCategoryScreenDestination.itemIdArg)

            GamesListCategoryScreen(
                gameGenre = gameGenre ?: "",
                modifier = Modifier,
                onClick = { navController.navigate("${ItemDetailsDestination.route}/${it}") },
                onBack = {
                    navController.navigate(Pantalla1.route) {
                        launchSingleTop = false
                    }
                },
                onGenreClick = { genre ->
                    navController.navigate("${GameListCategoryScreenDestination.route}/${genre}")
                },
            )
        }
    }
}



