package com.example.games.ui.screens

//01/12
/**
 **/

/**
@RequiresApi(34)
@Composable
fun HomeScreen(
    gameUiState: GameUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,


    ) {

    when (gameUiState) {

        is GameUiState.Loading -> LoadingScreen(modifier)

        is GameUiState.Success -> NotPlayedScreen(onClick = {  }, onGenreClick = {}) //{}// GameListScreen(games = gameUiState.games)

        else -> ErrorScreen(retryAction, modifier)
    }
}*/


