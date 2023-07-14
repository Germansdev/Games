package com.example.games

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.Pantalla1
import com.example.games.appDestinations.Graph
import com.example.games.appDestinations.NavigationDestination
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.CustomTopBar
import com.example.games.ui.GameApp
import com.example.games.ui.SharedScreen
import com.example.games.ui.currentRoute
import com.example.games.ui.screens.DetailsScreen
import com.example.games.ui.screens.FavoritesScreen
import com.example.games.ui.screens.ItemDetailsDestination
import com.example.games.ui.screens.NotPlayedScreen
import com.example.games.ui.screens.Played

import com.example.games.ui.screens.Statistics
import com.example.games.ui.theme.GameIcons


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun GameNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = Pantalla1.route,
    navController: NavHostController,
) {

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
                modifier = Modifier,
                onClick = {
                    navController.navigate("${ItemDetailsDestination.route}/${it}")
                },
            )
        }

        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            val viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)

            DetailsScreen(
                gameDetails = Game(),
                onClick = { navController.popBackStack() },
                modifier,
                viewModel,
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

       composable(route= SearchScreenDestination.route){
          CustomTopBar(
              titleRes = R.string.search,
              navigationIcon = GameIcons.Search,
              navigationIconContentDescription = null,
              actionIcon = GameIcons.Close,//GameIcons.Settings,
              actionIconContentDescription = null,
              onNavigationClick = {navController.navigateToSearch()/**.navigate(/**searchRoute*/)*/},
          )
           //searchScreen (onBackClick = navController::popBackStack)

       }
        //searchScreen (onBackClick = navController::popBackStack)


        composable(route= SearchScreenDestination.route){
            SearchScreen (
            onBackClick = { navController.popBackStack() }
            )
            searchScreen (
               onBackClick = navController::popBackStack
            )
        }
        /**
        searchScreen (
            onBackClick = navController::popBackStack
        )*/
      // searchGraph(navController)
    }
}


@RequiresApi(Build.VERSION_CODES.R)
fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = ItemDetailsDestination.routeWithArgs,

        ) {
        composable(
            route = ItemDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                NavType.IntType
            })
        ) {
            val viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory)
            DetailsScreen(
                gameDetails = Game(),
                onClick = { navController.popBackStack() },
                Modifier,
                viewModel,
            )
        }
    }
}
/**
//@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.searchGraph(navController: NavHostController)
{
navigation(
    startDestination = CustomTopBar(
        titleRes = ,
        navigationIcon = ,
        navigationIconContentDescription = ,
        actionIcon = ,
        actionIconContentDescription =
    )
)
}*/
