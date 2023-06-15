package com.example.games.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.games.R
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
        if (viewModel.favorites.value.isNotEmpty()) {
            when (gameUiState) {
                is GameUiState.Loading -> LoadingScreen(modifier)
                is GameUiState.Success -> GameListScreen(
                    modifier, gameUiState.games

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
        }
    }
}


