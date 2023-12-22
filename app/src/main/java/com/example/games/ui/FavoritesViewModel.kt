package com.example.games.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.ItemsRepository
import com.example.games.data.util.ConnectivityObserver
import com.example.games.model.Game
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel(
    itemsRepository: ItemsRepository,

):ViewModel() {

    val favoritesUiState: StateFlow<FavoritesUiState> =
        itemsRepository.getAllFavoritesStream(isFavorite = true)
            .filterNotNull()
            .map { FavoritesUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = FavoritesUiState()
            )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class FavoritesUiState ( val favoritesL: List<Game?> = listOf())


