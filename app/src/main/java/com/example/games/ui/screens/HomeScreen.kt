package com.example.games.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.games.ui.GameUiState
import com.example.games.ui.GameViewModel

@Composable
fun HomeScreen(
    gameUiState: GameUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    ){

    when (gameUiState){
        is GameUiState.Loading -> LoadingScreen (modifier)

        is GameUiState.Success -> GameListScreen(
           gameViewModel = GameViewModel(gameRepository= viewModel()),
        )
        else -> ErrorScreen(retryAction, modifier)
    }

}