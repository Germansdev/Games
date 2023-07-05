package com.example.games

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.Pantalla1
import com.example.games.appDestinations.DetailsDestination
import com.example.games.model.GameDetails
import com.example.games.ui.SharedScreen
import com.example.games.ui.screens.DetailsScreen
import com.example.games.ui.screens.FavoritesScreen
import com.example.games.ui.screens.NotPlayedScreen
import com.example.games.ui.screens.Played


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun GameNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = Pantalla1.route,
    navController: NavHostController,
    ) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Pantalla1.route) {
           // val gameViewModel: GameViewModel =
             //   viewModel(factory = GameViewModel.Factory)
/**
            HomeScreen(
                gameUiState = gameViewModel.gameUiState,
                retryAction = gameViewModel::getGames,
                modifier = modifier,
                //navController.navigate($ { DetailsDestination.DetailsScreen })
            )

            GameListScreen(
                games = gameViewModel.games.value,

                //onClick = {navController.navigate(DetailsDestination.DetailsScreen.title)}
                onClick = {
                    navController.navigate("${DetailsDestination.DetailsScreen.title}/$it") }
            )*/

                NotPlayedScreen()

        }

        composable(BottomBarScreen.Pantalla2.route) {
            FavoritesScreen()
        }

        composable(BottomBarScreen.Pantalla3.route) {
            Played()
        }

        composable(BottomBarScreen.Pantalla4.route) {
            NotPlayedScreen()
        }

        composable(BottomBarScreen.Pantalla5.route) {
            SharedScreen()
        }
        //composable(route = GameListScreen()){}

        composable(
            route = DetailsDestination.DetailsScreen.title

        ) {
          /**   val detailsViewModel: DetailsViewModel =
            viewModel(factory = DetailsViewModel.Factory)*/
            // detailViewModel.uiState

            DetailsScreen(
                navigateBack = { navController.navigateUp() },
                viewModel = viewModel(),
                // gameDetailsUiState =
                gameDetails = GameDetails() //viewModel()
            )
        }
    }
}



/**
//RatedScreenNavigation:
fun NavController.navigateToRatedScreen(navOptions: NavOptions? = null) {
    this.navigate(ratedScreenRoute.toString(), navOptions)
}

fun NavGraphBuilder.ratedScreen(
    onBackClick: () -> Unit,

    ) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = ratedScreenRoute.toString()) {
        RatedRoute(
            modifier = Modifier,
            onBackClick = {  },

            )
    }
}
@Composable
internal fun RatedRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    // TODO("Not yet implemented")
    val gameViewModel: GameViewModel =
        viewModel(factory = GameViewModel.Factory)
    RatedScreen()
}
//SharedScreenNavigation:
fun NavController.navigateToSharedScreen(navOptions: NavOptions? = null) {
    this.navigate(sharedScreenRoute.toString(), navOptions)
}

fun NavGraphBuilder.sharedScreen(
    onBackClick: () -> Unit,

    ) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = sharedScreenRoute.toString()) {
        SharedRoute(
            modifier = Modifier,
            onBackClick = {  },

            )
    }
}
@Composable
internal fun SharedRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    // TODO("Not yet implemented")
    val gameViewModel: GameViewModel =
        viewModel(factory = GameViewModel.Factory)
    SharedScreen()
}
*/