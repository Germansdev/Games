package com.example.games.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.games.model.Game
import com.example.games.ui.GameUiState

private const val TAG: String = "games loaded GL_screen"
/**
@Composable
fun GameListScreen(
    /**
    //01/12
    gameUiState: GameUiState,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit,*/

    games: List<Game>,

) {
  /**
    //01/12:
    when (gameUiState) {

        is GameUiState.Loading -> LoadingScreen(modifier)

        is GameUiState.Success -> GameListScreen(games = gameUiState.games)

        else -> ErrorScreen(retryAction, modifier)
    }
*/
    if (games.isEmpty()) {

        NothingFoundScreen()

    } else {
        Log.d(TAG, games.size.toString())
    }
}
*/