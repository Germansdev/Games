package com.example.games.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.ItemsRepository
import com.example.games.model.Game
//import com.example.games.model.Genre
import com.example.games.ui.screens.GameListCategoryScreenDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class NotPlayedViewModel(
    itemsRepository: ItemsRepository,
   // savedStateHandle: SavedStateHandle,
) : ViewModel() {
// 01 /12 eliminado:
    val notPlayedUiState: StateFlow<NotPlayedUiState> =
        itemsRepository.getAllNotPlayedStream(isPlayed = false)
            .filterNotNull()
            .map { it ->
                NotPlayedUiState(it)
                //NotPlayedUiState(it.groupingBy { it!!.genre }.eachCount().toList().toMutableList())

            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                //initialValue = NotPlayedUiState(),
                initialValue = NotPlayedUiState()
            )




    /**
     *  PENDING: CREATE A genre viewModel to keep it separately from this each viewModel
     *  with its concern
     */
/**
//01 /12:
        val genreUiState: StateFlow<GenreUiState> =
            itemsRepository.getCategories()
                .filterNotNull()
                .map {
                    GenreUiState(it)
                }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue =
                    GenreUiState()
                )

*/

/**
   //add savedStatHandle, and this:
    private val gameGenre : String = checkNotNull(savedStateHandle[GameListCategoryScreenDestination.itemIdArg])
    val gamesListUiState: StateFlow<GameListCategoryUiState> =

        itemsRepository.getGamesByCategoryStream(gameGenre = gameGenre)
            .filterNotNull()

            .map {
                GameListCategoryUiState(it)}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = GameListCategoryUiState()
            )*/



    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    }


    data class NotPlayedUiState( val notPlayedL: List<Game?> = listOf())
