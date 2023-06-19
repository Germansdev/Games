package com.example.games.ui.screens

import androidx.compose.runtime.Composable
import com.example.games.model.Game
import com.example.games.ui.GameViewModel

@Composable
fun DetailsScreen(
    gameViewModel: GameViewModel,
    game: Game,
    //onClick :() -> Unit
){
    GameCard(
        gameViewModel,
        game,
        onClick = {}
    )
}