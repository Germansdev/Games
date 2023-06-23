package com.example.games.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.games.ui.GameUiState
import com.example.games.ui.GameViewModel


@Composable
fun FavoritesScreen(
    viewModel: GameViewModel,
    //favorites : State<Set<String>>,

    gameUiState: GameUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    //favorites:
     // val gameUiState = viewModel.gameUiState.collectAsState().value,

    Column {
       //with database no way with gameUiState Flow:
        /**
        if (viewModel.favorites.value.isNotEmpty()) {
            when (gameUiState) {
                is GameUiState.Loading -> LoadingScreen(modifier)
                is GameUiState.Success -> GameListScreen(
                    modifier,
                    //previous only fetch:
                   // gameUiState.games
                //with database:
                gameUiState.Flow<List<Game>>

                )

                else -> ErrorScreen(retryAction, modifier)
            }
        } else {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.no_favorite_games))
            }
        }*/
    }
}


