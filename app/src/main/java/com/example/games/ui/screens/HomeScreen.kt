package com.example.games.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.games.appDestinations.GamesNavigationType
import com.example.games.ui.GameUiState

//01/12
/**
 **/
@Composable
fun HomeScreen(
    gameUiState: GameUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,


) {

    when (gameUiState) {

        is GameUiState.Loading -> LoadingScreen(modifier)

        is GameUiState.Success -> NotPlayedScreen(onClick = {  }, onGenreClick ={} )// GameListScreen(games = gameUiState.games)

        else -> ErrorScreen(retryAction, modifier)
    }
}


