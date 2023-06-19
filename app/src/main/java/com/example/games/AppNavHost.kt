package com.example.games

import android.telecom.Call.Details
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.Pantalla1
import com.example.games.model.Game
import com.example.games.ui.GameUiState
import com.example.games.ui.GameViewModel
import com.example.games.ui.SharedScreen
import com.example.games.ui.screens.DetailsScreen
import com.example.games.ui.screens.FavoritesScreen
import com.example.games.ui.screens.GameCard
import com.example.games.ui.screens.GameListScreen
import com.example.games.ui.screens.HomeScreen
import com.example.games.ui.screens.Played
import com.example.games.ui.screens.RatedScreen


//NAVIGATE WITH COMPOSE COMPONENTS BUTON , ETC.

@Composable
fun GameNavHost(
    //with CenterAlignedTopAppBar, add gameState:
    //gameState: GameState,
    modifier: Modifier = Modifier,
    //with CenterAlignedTopAppBar, elevate value of startDestination:
    startDestination: String = Pantalla1.route,

    navController: NavHostController,


    ) {
    //with CenterAlignedTopAppBar:
    //val navController = gameState.navController

    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
    val games = viewModel.games.value

    NavHost(
        navController = navController,

        //with CenterAlignedTopAppBar, elevate the state of startDestination to parameter to GameNavHost:
        //startDestination = Pantalla1.route,
        startDestination = startDestination,
        modifier = modifier
    ) {


//START previous, without CenterAlignedTopAppBar:

        composable(Pantalla1.route) {
            /** HomeScreen(
            gameUiState = GameUiState.Loading,
            retryAction =  gameViewModel::getGames )*/
            val gameViewModel: GameViewModel =
                viewModel(factory = GameViewModel.Factory)
            HomeScreen(
                gameUiState = gameViewModel.gameUiState,
                retryAction = gameViewModel::getGames,
                modifier = modifier,
            )
        }
        /**
        composable(Pantalla2.route){
        GameListScreen(games = viewModel.games.value)
        }*/

        composable(BottomBarScreen.Pantalla2.route) {
            FavoritesScreen(
                viewModel = viewModel,
                gameUiState = viewModel.gameUiState,
                retryAction = { viewModel.getGames() })

            //Log.d(com.example.games.ui.screens.TAG, .toString())
        }

        composable(BottomBarScreen.Pantalla3.route) {
            Played()
        }

        composable(BottomBarScreen.Pantalla4.route) {
            RatedScreen()
        }

        composable(BottomBarScreen.Pantalla5.route) {
            SharedScreen()
        }

        composable(route = detailsDestination.DetailsScreen.name){
           // val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
           // viewModel.getGames()
            // DetailsScreen(gameViewModel = viewModel,
              //  game = ( game) -> Unit

            }
        }

    //END previous


    }


enum class detailsDestination (val title: String){
 DetailsScreen(title = "Details")
}

/**
//with CenterAlignedTopAppBar:
val homeScreenRoute = Pantalla1
val favoritesScreenRoute = Pantalla2
val playedScreenRoute = Pantalla3
val ratedScreenRoute = Pantalla4
val sharedScreenRoute = Pantalla5

//HomeScreenNavigation:
fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) {
    this.navigate(homeScreenRoute.toString(), navOptions)
}

fun NavGraphBuilder.homeScreen(
    onBackClick: () -> Unit,

    ) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = homeScreenRoute.toString()) {
        HomeRoute(
            modifier = Modifier,
            onBackClick = {  },

            )
    }
}
@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    //TODO("Not yet implemented")
    val gameViewModel: GameViewModel =
        viewModel(factory = GameViewModel.Factory)
    HomeScreen(
        gameUiState = gameViewModel.gameUiState,
        retryAction = gameViewModel::getGames,
        modifier = modifier,
    )
}


//FavoritesScreenNavigation:
fun NavController.navigateToFavoritesScreen(navOptions: NavOptions? = null) {
    this.navigate(favoritesScreenRoute.toString(), navOptions)
}

fun NavGraphBuilder.favoritesScreen(
    onBackClick: () -> Unit,

    ) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = favoritesScreenRoute.toString()) {
        FavoritesRoute(
            modifier = Modifier,
            onBackClick = {  },
            )
    }
}
@Composable
internal fun FavoritesRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
   // TODO("Not yet implemented")
    val gameViewModel: GameViewModel =
        viewModel(factory = GameViewModel.Factory)
    FavoritesScreen(
        viewModel = viewModel(),
        gameUiState = gameViewModel.gameUiState,
        retryAction = { gameViewModel.getGames() },
    )
}

//PlayedScreenNavigation:
fun NavController.navigateToPlayedScreen(navOptions: NavOptions? = null) {
    this.navigate(playedScreenRoute.toString(), navOptions)
}

fun NavGraphBuilder.playedScreen(
    onBackClick: () -> Unit,

    ) {
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = playedScreenRoute.toString()) {
        PlayedRoute(
            modifier = Modifier,
            onBackClick = {  },

            )
    }
}
@Composable
internal fun PlayedRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    // TODO("Not yet implemented")
    val gameViewModel: GameViewModel =
        viewModel(factory = GameViewModel.Factory)
    Played()
}

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