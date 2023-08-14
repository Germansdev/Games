package com.example.games.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.games.model.Game
import com.example.games.ui.GameUiState


@Composable
fun HomeScreen(
    gameUiState: GameUiState,
    //itemsRepository: ItemsRepository,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    ){

    when (gameUiState){
        is GameUiState.Loading -> LoadingScreen (modifier)

        is GameUiState.Success -> GameListScreen(
           /** NotPlayedScreen*/
            modifier,

            games = gameUiState.games,

            //navigateToGameDetails = {  },
            onClick = {},

        )
        else -> ErrorScreen(retryAction, modifier)
    }
}


