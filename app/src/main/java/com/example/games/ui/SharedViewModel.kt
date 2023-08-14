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

class SharedViewModel(
    itemsRepository: ItemsRepository
): ViewModel() {
    val sharedUiState: StateFlow<SharedUiState> =
        itemsRepository.getAllSharedStream(isShared = true)
            .filterNotNull()
            .map { SharedUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = SharedUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class SharedUiState (val sharedL: List<Game?> = listOf() )