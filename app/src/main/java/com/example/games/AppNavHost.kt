package com.example.games

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.games.appDestinations.BottomBarScreen.Pantalla1
import com.example.games.appDestinations.BottomBarScreen.Pantalla2
import com.example.games.appDestinations.BottomBarScreen.Pantalla3
import com.example.games.appDestinations.BottomBarScreen.Pantalla4
import com.example.games.appDestinations.BottomBarScreen.Pantalla5
import com.example.games.ui.GameUiState
import com.example.games.ui.GameViewModel
import com.example.games.ui.SharedScreen
import com.example.games.ui.screens.FavoritesScreen
import com.example.games.ui.screens.GameListScreen
import com.example.games.ui.screens.HomeScreen
import com.example.games.ui.screens.Played
import com.example.games.ui.screens.RatedScreen


//NAVIGATE WITH COMPOSE COMPONENTS BUTON , ETC.

@Composable
fun GameNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,

) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
   // val games = viewModel.games.value

    NavHost(
        navController = navController,
        startDestination = Pantalla1.route,
        modifier = modifier
    ) {

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

        composable(Pantalla2.route) {
            FavoritesScreen(
                viewModel = viewModel,
                gameUiState = viewModel.gameUiState,
                retryAction = { viewModel.getGames() })

            //Log.d(com.example.games.ui.screens.TAG, .toString())
        }

        composable(Pantalla3.route) {
        Played()
        }

        composable(Pantalla4.route) {
            RatedScreen()
        }

        composable(Pantalla5.route) {
           SharedScreen()
        }

    }
}
