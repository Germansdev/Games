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



class RatedViewModel(

): ViewModel() {
    //rated variables:

    var ratedGames: MutableList<Game> by mutableStateOf(mutableListOf<Game>())
        private set

    var ratedUiState: GameUiState by mutableStateOf(GameUiState.Loading)
        private set



//RatingUiState

   // private val _ratingUiState = MutableStateFlow(RatingUiState())

   // val ratingUiState: StateFlow<RatingUiState> = _ratingUiState.asStateFlow()


//var rating: Float by mutableStateOf(1.0f)


//replacing with and use this viewModel with rating Bar:
    /**
fun isGameRating(rating: Float):Float{
return rating
}

fun isGameRating(game: Game):Boolean{

 }
    */

    fun isGameRated(game: Game): Boolean {
        return !ratedGames.filter { x -> x.id == game.id }.isEmpty()
    }


    fun addRatedGame(game: Game) {
        if (!isGameRated(game)) {
            ratedGames.add(game)
            ratedGamesUpdated()
        }
    }

    fun removeRatedGame(game: Game) {
        ratedGames.removeIf { it.id == game.id }
        ratedGamesUpdated()
    }


    private fun ratedGamesUpdated() {
        viewModelScope.launch {
            ratedUiState = GameUiState.Loading
            ratedUiState = GameUiState.Success(ratedGames)

        }
    }

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
