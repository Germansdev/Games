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
import com.example.games.model.Genre
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface GameUiState {
    data class Success(
        val games: List<Game> = listOf()) : GameUiState
    object Error : GameUiState
    object Loading : GameUiState
}

class GameViewModel(

    private val itemsRepository: ItemsRepository,
    private val gameRepository: GameRepository,

    ) : ViewModel() {

    var gameUiState: GameUiState by mutableStateOf(GameUiState.Loading)
        private set

    private val _games = mutableStateOf<List<Game>>(emptyList())
    val games: State<List<Game>> = _games

    private val _favorites = mutableStateOf<Set<Int>>(setOf())
    val favorites: State<Set<Int>> = _favorites

    private val _play = mutableStateOf<Set<Int>>(setOf())
    val play: State<Set<Int>> = _play

    private val _share = mutableStateOf<Set<Int>>(setOf())
    val share: State<Set<Int>> = _share

    private val _rate = mutableStateOf<Set<Int>>(setOf())
    var rate: State<Set<Int>> = _rate

    var rating = mutableStateOf(Float)

    var selectedRating = mutableStateOf(Float)

    //init block:
    init {
        getGames()
        getItems()

      //  getShooterGames()
    //generateCategory()


    }

    //Try to fetch games from api:
  private fun getGames() {
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

    /**
     * Holds home ui state. The list of items are retrieved from [ItemsRepository] and mapped to
     * [HomeUiState]
     */

    val homeUiState: StateFlow<HomeUiState> =
        itemsRepository.getAllItemsStream()
            .map { HomeUiState() }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )

    //to insert all items fetched from api to db:
    private fun getItems() {
        viewModelScope.launch {
            gameRepository.getGames()
            itemsRepository.insertAll(gameRepository.getGames())
        }
    }


    //logic favorite, play, share, rate:
    suspend fun isFavoriteGame(game: Game) {

        if (isFavorite(gameId = game.id)
        ) {
            itemsRepository.updateItem(game.copy(isFavorite = true))
            itemsRepository.updateItem(game.copy(favorited = 1))
        } else {
            itemsRepository.updateItem(game.copy(isFavorite = false))
            itemsRepository.updateItem(game.copy(favorited = 0))
        }
    }



    fun selectFavorite(gameId: Int) {
        val updatedFavorites = _favorites.value.toMutableSet()
        if (updatedFavorites.contains(gameId)) {
            updatedFavorites.remove(gameId)
        } else {
            updatedFavorites.add(gameId)
        }
        _favorites.value = updatedFavorites
    }

    suspend fun isPlayedGame(game: Game) {

        if (isPlay(gameId = game.id)
        ) {
            itemsRepository.updateItem(game.copy(isPlayed = true))
            itemsRepository.updateItem(game.copy(played = 1))
            itemsRepository.updateItem(game.copy(countPlayed = game.countPlayed+1))
        } else {
            itemsRepository.updateItem(game.copy(isPlayed = false))
            itemsRepository.updateItem(game.copy(played = 0))
        }
    }


    fun selectPlayed(gameId: Int) {
        val updatedPlay = _play.value.toMutableSet()
        if (updatedPlay.contains(gameId)) {
            updatedPlay.remove(gameId)
        } else {
            updatedPlay.add(gameId)
        }
        _play.value = updatedPlay
    }

    suspend fun isSharedGame(game: Game) {

        if (isShare(gameId = game.id)
        ) {
            itemsRepository.updateItem(game.copy(isShared = true))
            itemsRepository.updateItem(game.copy(shared = 1))

        } else {
            itemsRepository.updateItem(game.copy(isShared = false))
            itemsRepository.updateItem(game.copy(shared = 0))
        }
    }

    fun selectShared(gameId: Int) {
        val updatedShare = _share.value.toMutableSet()
        if (updatedShare.contains(gameId)) {
            updatedShare.remove(gameId)
        } else {
            updatedShare.add(gameId)
        }
        _share.value = updatedShare
    }

    suspend fun isRating(game: Game) {

        if (isRate(gameId = game.id/**.toString()*/)
        ) {
            itemsRepository.updateItem(game.copy(rating = game.rating))
        } /*else {
          //  itemsRepository.updateItem(game.copy(rating = 0f))
        }*/
    }
   suspend fun updateRating(game: Game){
        itemsRepository.updateItem(game.copy(rating = game.rating))
    }

    fun selectRate(gameId: Int) {
        val updatedRated = _rate.value.toMutableSet()
        if (updatedRated.contains(gameId)) {
            updatedRated.remove(gameId)
        } else {
            updatedRated.add(gameId)
        }
        _rate.value = updatedRated
    }

    fun isFavorite(gameId: Int): Boolean {
        return _favorites.value.contains(gameId)
    }

    fun isPlay(gameId: Int): Boolean {
        return _play.value.contains(gameId)
    }

    fun isShare(gameId: Int): Boolean {
        return _share.value.contains(gameId)
    }

    fun isRate(gameId: Int): Boolean {
        return _rate.value.contains(gameId)
    }

    val genreUiState: StateFlow<GenreUiState> =
        itemsRepository.getCategories()
            .filterNotNull()
            .map {
                GenreUiState(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = GenreUiState()

            )

 /**   val listUiState: StateFlow<ListUiState> =
        itemsRepository.getAllItemsStream()
            .map {
                ListUiState()
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ListUiState()
            )
*/


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GameApplication)
                val itemsRepository = application.container.itemsRepository
                val gameRepository = application.container.gameRepository

                GameViewModel(
                    itemsRepository = itemsRepository,
                    gameRepository = gameRepository,
                )
            }
        }
        private const val TIMEOUT_MILLIS = 5_000L
    }


}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(
    val itemList: List<Game> = listOf()
)
data class GenreUiState( val genreList: List<Genre> = listOf())

/**
data class ListUiState( val itemList: List<Game> = listOf())
        */