package com.example.games.ui


import androidx.annotation.RequiresApi
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
import com.example.games.data.GameNetworkDataSource
import com.example.games.data.ItemsRepository
import com.example.games.data.util.ConnectivityObserver
import com.example.games.model.Game
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface GameUiState {
    data class Success( val games: List<Game>) : GameUiState
    object Error: GameUiState
    object Loading : GameUiState
}

@RequiresApi(34)
class GameViewModel(

    private val itemsRepository: ItemsRepository,
    private val gameNetworkDataSource: GameNetworkDataSource,
    var status: ConnectivityObserver.Status,

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

       if (status== ConnectivityObserver.Status.Available)
        {
           getGames()
            getItems()

             } else{
                getItems()
        }
    }

    @RequiresApi(34)

    private fun getGames() {
        viewModelScope.launch {
            gameUiState = GameUiState.Loading
            if (status==ConnectivityObserver.Status.Available){
                gameUiState = try {
                    GameUiState.Success(gameNetworkDataSource.getGames())

                }catch (e: IOException) {
                    GameUiState.Error
                }
            }else{
                GameUiState.Error
            }
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            if (games.value.isEmpty()){
                    if (status==ConnectivityObserver.Status.Available){

                        try {
                            //gameNetworkDataSource.getGames()
                            itemsRepository.insertAll(gameNetworkDataSource.getGames())
                            }catch (e: IOException) {
                                GameUiState.Error }
                        }else{
                            itemsRepository.getAllItemsStream() }
                }else{
                    itemsRepository.getAllItemsStream()
            }
        }
    }

    /** xxxx logic favorite, play, share, rate:xxxxxxxxxxxxxx */

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



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as GameApplication)
                val itemsRepository = application.container.itemsRepository
                val gameRepository = application.container.gameNetworkDataSource

                GameViewModel(
                    itemsRepository = itemsRepository,
                    gameNetworkDataSource = gameRepository,
                    status = ConnectivityObserver.Status.Available
                )
            }
        }
   //     private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * Ui State for HomeScreen
 */
data class HomeUiState(
    val itemList: List<Game> = listOf()
)
