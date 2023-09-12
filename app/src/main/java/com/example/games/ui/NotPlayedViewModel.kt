package com.example.games.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.ItemsRepository
import com.example.games.model.Game
import com.example.games.model.Genre
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class NotPlayedViewModel(itemsRepository: ItemsRepository) : ViewModel() {

    val notPlayedUiState: StateFlow<NotPlayedUiState> =
        itemsRepository.getAllNotPlayedStream(isPlayed = false)
            .filterNotNull()
            .map {
                NotPlayedUiState(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = NotPlayedUiState()
            )
    /**
     *  PENDING: CREATE A genre viewModel to keep it separately from this each viewModel
     *  with its concern
     */


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





    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    }


    data class NotPlayedUiState( val notPlayedL: List<Game?> = listOf())
//    data class GenreUiState( val genreList: List<Genre> = listOf())


 /**   fun upDateBadge(notPlayedL: List<Game?>): Int {
        // BottomBarScreen.Pantalla1.badgeCount = notPlayedL.size
        return notPlayedL.size
    }*/