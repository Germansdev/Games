package com.example.games.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.games.model.Game
import com.example.games.ui.GameUiState

private const val TAG: String = "games loaded GL_screen"

@Composable
fun GameListScreen(
    //19 08
  //  gameUiState: GameUiState
    games: List<Game>
) {


    if (games.isEmpty()) {

        NothingFoundScreen()

    } else {
        Log.d(TAG, games.size.toString())
    }
}