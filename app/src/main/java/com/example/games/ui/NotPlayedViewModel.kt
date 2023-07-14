package com.example.games.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.GameRepository
import com.example.games.data.ItemsRepository
import com.example.games.model.Game
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotPlayedViewModel(
    itemsRepository: ItemsRepository,
   )
    : ViewModel() {

    val notPlayedUiState: StateFlow<NotPlayedUiState> =
        itemsRepository.getAllNotPlayedStream(isPlayed = false)
            .filterNotNull()
            .map { NotPlayedUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = NotPlayedUiState()
            )


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class NotPlayedUiState (val notPlayedL: List<Game?> = listOf() )