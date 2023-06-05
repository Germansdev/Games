package com.example.games.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.games.GameApplication
import com.example.games.model.Game
import com.example.games.ui.GameUiState
import com.example.games.ui.GameViewModel
import kotlinx.coroutines.launch

class ShareViewModel : ViewModel() {
    //play variables:

    var sharedGames: MutableList<Game> by mutableStateOf(mutableListOf<Game>())
        private set

    var sharedUiState: GameUiState by mutableStateOf(GameUiState.Loading)
        private set




    fun isGameShared(game: Game): Boolean {
        return !sharedGames.filter { x -> x.id == game.id }.isEmpty()
    }


    fun addSharedGame(game: Game) {
        if (!isGameShared(game)) {
            sharedGames.add(game)
            sharedGamesUpdated()
        }
    }

    fun removeSharedGame(game: Game) {
        sharedGames.removeIf { it.id == game.id }
        sharedGamesUpdated()
    }


    private fun sharedGamesUpdated() {
        viewModelScope.launch {
            sharedUiState = GameUiState.Loading
            sharedUiState = GameUiState.Success(sharedGames)

        }
    }

    //for ShareViewModel:


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[

                        ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)
                val gameRepository = application.container.gameRepository
                GameViewModel(gameRepository = gameRepository)
            }
        }
    }
}
