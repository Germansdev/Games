package com.example.games.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.games.ui.GameUiState

@Composable
fun HomeScreen(
    gameUiState: GameUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    ){

    when (gameUiState){
        is GameUiState.Loading -> LoadingScreen (modifier)

        is GameUiState.Success -> GameListScreen(modifier,gameUiState.games

        )
        else -> ErrorScreen(retryAction, modifier)
    }
}