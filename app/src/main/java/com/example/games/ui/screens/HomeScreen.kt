package com.example.games.ui.screens

/**
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
            modifier,

            games = gameUiState.games,

            //navigateToGameDetails = {  },
            onClick = {},

        )
        else -> ErrorScreen(retryAction, modifier)
    }
}
*/