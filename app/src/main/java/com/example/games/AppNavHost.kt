package com.example.games
/**
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.games.AppDestinations.BottomBarScreen
import com.example.games.ui.GameViewModel
import com.example.games.ui.screens.FavoritesScreen
import com.example.games.ui.screens.GameListScreen
import com.example.games.ui.screens.HomeScreen
import com.example.games.ui.screens.Played
import com.example.games.ui.screens.RatedScreen


//NAVIGATE WITH COMPOSE COMPONENTS BUTON , ETC.

@Composable
fun GameNavHost(
    navController: NavHostController,
    //gameUiState: GameUiState,
    modifier: Modifier = Modifier,

) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
   // val games = viewModel.games.value

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Pantalla3.route,
        //startDestination = BottomBarScreen.ListScreen.route,
        //modifier = modifier
    ) {

        composable(BottomBarScreen.Pantalla3.route) {
            Played()
        }

        composable(BottomBarScreen.Pantalla2.route) {
            FavoritesScreen(
                viewModel = viewModel,
                gameUiState = viewModel.gameUiState,
                retryAction = { viewModel.getGames() })

            //Log.d(com.example.games.ui.screens.TAG, .toString())
        }
        /**
        composable(BottomBarScreen.Pantalla3.route) {
        com.example.games.ui.screens.Played()
        }
         */
        /**
        composable(BottomBarScreen.Pantalla3.route) {
        com.example.games.ui.screens.Played()
        }
         */
        composable(BottomBarScreen.Pantalla4.route) {
            RatedScreen()
        }
        /**
        composable(BottomBarScreen.Pantalla5.route) {
        com.example.games.ui.screens.Prueba()
        }*/
        /**
        composable(BottomBarScreen.Pantalla5.route) {
        com.example.games.ui.screens.Prueba()
        }*/

        composable(BottomBarScreen.Pantalla6.route) {

            HomeScreen(
                gameUiState = viewModel.gameUiState,
                retryAction = { viewModel.getGames() },
                modifier = Modifier
            )
        }
        composable(BottomBarScreen.Pantalla7.route) {
            GameListScreen(
                modifier,viewModel.games.value,
                //modifier = Modifier, games = List<Game>(),
               // games = viewModel(),

            )
        }

    }
}
        */