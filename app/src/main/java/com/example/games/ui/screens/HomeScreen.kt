package com.example.games.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.games.ui.GameUiState

@Composable
fun HomeScreen(
    gameUiState: GameUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    ){

    when (gameUiState){
        is GameUiState.Loading -> LoadingScreen (modifier)

        is GameUiState.Success -> GameListScreen(
            viewModel = viewModel(),
            favoriteViewModel = FavoriteViewModel(),
            shareViewModel = ShareViewModel(),
            playViewModel = PlayViewModel(),
            ratedViewModel = RatedViewModel(),
            games = gameUiState.games,
            gameUiState = gameUiState
        )
        else -> ErrorScreen(retryAction, modifier)
    }

}