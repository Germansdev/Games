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


class FavoriteViewModel(
//private val gameRepository: GameRepository
): ViewModel() {
    //favorite variables:

    var favoriteGames: MutableList<Game> by mutableStateOf(mutableListOf<Game>())
        private set

    var favoritesfUiState: GameUiState by mutableStateOf(GameUiState.Loading)
        private set

//favorite logic:
    fun isGameFavorite(game: Game): Boolean {
        return !favoriteGames.filter { x -> x.id == game.id }.isEmpty()
    }
    fun addFavoriteGame(game: Game) {
        if (!isGameFavorite(game)) {
            favoriteGames.add(game)
            favoritesUpdated()
        }
    }
    fun removeFavoriteGame(game: Game) {
        favoriteGames.removeIf { it.id == game.id }
        favoritesUpdated()
    }
    private fun favoritesUpdated() {
        viewModelScope.launch {
            favoritesfUiState = GameUiState.Loading
            favoritesfUiState = GameUiState.Success(favoriteGames)

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[
                        /**ViewModelProvider.AndroidViewModelFactory.*/
                        /**ViewModelProvider.AndroidViewModelFactory.*/
                        ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)
                val gameRepository = application.container.gameRepository
                //27 05 change GameViewModel to FavoriteViewModel:
                GameViewModel(gameRepository = gameRepository)
            }
        }
    }
}
