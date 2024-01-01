package com.example.games.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.ItemsRepository
import com.example.games.model.Game
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class PlayedViewModel(
    itemsRepository: ItemsRepository,
)
    : ViewModel() {
    val playedUiState: StateFlow<PlayedUiState> =
        itemsRepository.getAllPlayedStream(isPlayed = true)
            .filterNotNull()
            .map { PlayedUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = PlayedUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class PlayedUiState (val playedL: List<Game?> = listOf() )


