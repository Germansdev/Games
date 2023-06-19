package com.example.games.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.games.GameApplication
import com.example.games.data.GameRepository
import com.example.games.model.Game
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface GameUiState {
    data class Success(val games: List<Game>) : GameUiState
    object Error : GameUiState
    object Loading : GameUiState
}

class GameViewModel(
    private val gameRepository: GameRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {



    var gameUiState: GameUiState by mutableStateOf(GameUiState.Loading)
      private set

    private val _games = mutableStateOf<List<Game>>(emptyList())
    val games: State<List<Game>> = _games

    private val _favorites = mutableStateOf<Set<String>>(setOf())
    val favorites: State<Set<String>> = _favorites

    private val _play = mutableStateOf<Set<String>>(setOf())
    val play: State<Set<String>> = _play

    private val _share = mutableStateOf<Set<String>>(setOf())
    val share: State<Set<String>> = _share


    //init block:
    init {
        getGames()
    }



    //GAMES FROM
    fun getGames() {
        viewModelScope.launch {
            gameUiState = GameUiState.Loading
            gameUiState = try {
                GameUiState.Success(gameRepository.getGames())
            } catch (e: IOException) {
                GameUiState.Error
            } catch (e: HttpException) {
                GameUiState.Error
            }
        }
    }


    fun selectFavorite(gameId: String) {
        val updatedFavorites = _favorites.value.toMutableSet()
        if (updatedFavorites.contains(gameId)) {
            updatedFavorites.remove(gameId)
        } else {
            updatedFavorites.add(gameId)
        }
        _favorites.value = updatedFavorites
    }

    fun selectPlayed(gameId: String) {
        val updatedPlay = _play.value.toMutableSet()
        if (updatedPlay.contains(gameId)) {
            updatedPlay.remove(gameId)
        } else {
            updatedPlay.add(gameId)
        }
        _play.value = updatedPlay
    }

    fun selectShared(gameId: String) {
        val updatedShare = _share.value.toMutableSet()
        if (updatedShare.contains(gameId)) {
            updatedShare.remove(gameId)
        } else {
            updatedShare.add(gameId)
        }
        _share.value = updatedShare
    }

    fun isFavorite(gameId: String): Boolean {
        return _favorites.value.contains(gameId)
    }

    fun isPlay(gameId: String): Boolean {
        return _play.value.contains(gameId)
    }

    fun isShare(gameId: String): Boolean {
        return _share.value.contains(gameId)
    }





    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                val application = (this[APPLICATION_KEY] as GameApplication)
                val gameRepository = application.container.gameRepository
                GameViewModel(gameRepository = gameRepository,
                savedStateHandle = savedStateHandle)
            }
        }
    }
}
