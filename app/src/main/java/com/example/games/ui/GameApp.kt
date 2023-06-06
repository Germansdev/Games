package com.example.games.ui


import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.games.AppDestinations.BottomBarScreen.*
import com.example.games.ui.screens.HomeScreen


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameApp(
    modifier: Modifier = Modifier
) {
    val gameViewModel: GameViewModel =
        viewModel(factory = GameViewModel.Factory)
    HomeScreen(
        gameUiState = gameViewModel.gameUiState,
        retryAction = gameViewModel::getGames,
        modifier = modifier,
    )
}