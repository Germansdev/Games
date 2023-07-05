package com.example.games

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.appDestinations.DetailsDestination
import com.example.games.data.ItemsRepository
import com.example.games.model.GameDetails
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    itemsRepository: ItemsRepository
    ) : ViewModel() {

    private val itemId : Int = checkNotNull(savedStateHandle[DetailsDestination.DetailsScreen.toString()])

    val uiState: StateFlow<GameDetailsUiState> =
        itemsRepository.getItemStream(itemId)
            .filterNotNull()
            .map {GameDetailsUiState(GameDetails())}
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
data class GameDetailsUiState(
    val  gameDetails: GameDetails = GameDetails()
)
