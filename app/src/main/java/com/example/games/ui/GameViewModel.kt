package com.example.games.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.games.GameApplication
import com.example.games.data.GameRepository
import com.example.games.data.ItemsRepository
import com.example.games.model.Game
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface GameUiState {
    //this with only fetch:
    //data class Success(val games: List<Game>) : GameUiState

    //this with database, but changed without Flow:
    data class Success(val games: List<Game>) : GameUiState
    object Error : GameUiState
    object Loading : GameUiState
}

class GameViewModel(
   //previous only fetch:
    private val gameRepository: GameRepository,

    //using ItemRepository database:
    private val itemsRepository: ItemsRepository,

    //private val savedStateHandle: SavedStateHandle
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
//XXXXX

    /**
     * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     */
    val homeUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream().map { HomeUiState() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()

            )

   /**companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }*/

//xxxxxx

    //init block:
    init {
        getGames()
    }



    //GAMES FROM
    fun getGames() {
        viewModelScope.launch {
            gameUiState = GameUiState.Loading
            gameUiState = try {
                //previous only fetch:
                GameUiState.Success(gameRepository.getGames())
                //with database:
                //GameUiState.Success(itemsRepository.getAllItemsStream())
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
               //val savedStateHandle = createSavedStateHandle()
                val application = (this[APPLICATION_KEY] as GameApplication)

                //this only with fetch data:
               /** val gameRepository = application.container.gameRepository
                GameViewModel(gameRepository = gameRepository,

                  //  itemsRepository = itemsRepository
                //savedStateHandle = savedStateHandle
                )*/
                //this with ItemRepository database:
                val itemsRepository = application.container.itemsRepository
                val gameRepository = application.container.gameRepository
                GameViewModel(gameRepository = gameRepository ,itemsRepository = itemsRepository,
                    //savedStateHandle = savedStateHandle

                )

            }
        }
    private const val TIMEOUT_MILLIS = 5_000L
    }

}


//XXXX
/**
 * Ui State for HomeScreen
 */
data class HomeUiState(val itemList: List<Game> = listOf())
//XXXX