package com.example.games

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.ItemsRepository
import com.example.games.model.Game
import com.example.games.model.GameEntity
import com.example.games.model.asExternalModel
import com.example.games.ui.screens.ItemDetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    itemsRepository: ItemsRepository,
    /**With SavedStateHandle, keep the itemId to go to details*/
    savedStateHandle: SavedStateHandle,

    ) : ViewModel() {

    private val itemId : Int = checkNotNull(savedStateHandle[ItemDetailsDestination.itemIdArg])


    val uiState: StateFlow<GameDetailsUiState> =
        itemsRepository.getItemStream(itemId)
            .filterNotNull()

            .map {
                GameDetailsUiState(it)}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = GameDetailsUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * UI state for GameDetailsScreen
 */
data class GameDetailsUiState (
    val  gameDetails: Game = Game()
   // val  gameDetails: GameEntity = GameEntity()
)
