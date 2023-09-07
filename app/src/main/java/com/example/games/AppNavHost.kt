package com.example.games

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.Pantalla1
import com.example.games.appDestinations.Graph
import com.example.games.model.Game
import com.example.games.search.SearchScreen
import com.example.games.search.SearchScreenDestination
import com.example.games.search.navigateToSearch
import com.example.games.ui.CustomTopBar
import com.example.games.ui.GameState
import com.example.games.ui.GameViewModel
import com.example.games.ui.screens.DetailsScreen
import com.example.games.ui.screens.FavoritesScreen
import com.example.games.ui.screens.GameListCategoryScreenDestination
import com.example.games.ui.screens.GamesListCategoryScreen
import com.example.games.ui.screens.ItemDetailsDestination
import com.example.games.ui.screens.NotPlayedScreen
import com.example.games.ui.screens.Played
import com.example.games.ui.screens.SharedScreen
import com.example.games.ui.screens.Statistics
import com.example.games.ui.theme.GameIcons


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun GameNavHost(
    appState: GameState,
    modifier: Modifier = Modifier,
    startDestination: String = Pantalla1.route,
    viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory) // check after migrate db and see if charge games!!
) {
    val navController = appState.navController
     val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)  // check after migrate db and see if charge games!!

    NavHost(
        navController = navController,
        route = Graph.BOTTOM,
        startDestination = startDestination,
        modifier = modifier
    ) {

        composable(
            /**route = Graph.BOTTOM.*/
            route = Pantalla1.route
        ) {

            NotPlayedScreen(
                //with Graph.DETAILS without Args:
               onClick = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                },
               onGenreClick = {genre->
                    navController.navigate("${GameListCategoryScreenDestination.route}/${genre}")
                },
            )
        }

        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {

            DetailsScreen(
                gameDetails = Game(),
                onClick = { navController.popBackStack() },
                modifier,
            )
        }

        composable(BottomBarScreen.Pantalla2.route) {
            FavoritesScreen()
        }

        composable(BottomBarScreen.Pantalla3.route) {
            Played()
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
                navigationIcon = GameIcons.Search,
                navigationIconContentDescription = null,
                actionIcon = GameIcons.Close,
                actionIconContentDescription = null,
                onNavigationClick = {  navController.navigateToSearch() },
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
           route = GameListCategoryScreenDestination.routeWithArgs,
           arguments = listOf(navArgument(GameListCategoryScreenDestination.itemIdArg)
           {type = NavType.StringType }    )
       ){

           backStackEntry ->
           val gameGenre = backStackEntry.arguments?.getString(GameListCategoryScreenDestination.itemIdArg)
           GamesListCategoryScreen(
               gameGenre = gameGenre ?: "",
               modifier = Modifier,
               onClick = {
                   navController.navigate("${ItemDetailsDestination.route}/${it}")
               },
               onBack = {  navController.popBackStack() },
               onGenreClick = {genre->
                   navController.navigate("${GameListCategoryScreenDestination.route}/${genre}")
               },
           )
       }
    }
}


