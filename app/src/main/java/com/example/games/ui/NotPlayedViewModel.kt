package com.example.games.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.games.data.ItemsRepository
import com.example.games.model.Game
import com.example.games.model.Genre
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class NotPlayedViewModel(
    itemsRepository: ItemsRepository,
   ) : ViewModel() {

    val notPlayedUiState: StateFlow<NotPlayedUiState> =
        itemsRepository.getAllNotPlayedStream(isPlayed = false)
            .filterNotNull()
            .map {
                NotPlayedUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue =
                //NotPlayedUiState.Loading
                NotPlayedUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

   // private val notPlayedL = MutableStateFlow<List<Game>>(emptyList())
    //val myCategory = notPlayedL.map{ categoryListR -> categoryListR.map { it.genre}.distinct() }

    val genreUiState: StateFlow<GenreUiState> =
        itemsRepository
            //.insertGenreStream(genre = Genre(getCategories()))

            .getCategories()
            //.getGamesByCategoryStream(gameGenre = String())
            .filterNotNull()
            .map {
                GenreUiState(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue =
                //NotPlayedUiState.Loading
                GenreUiState()
            )
}
data class NotPlayedUiState(val notPlayedL: List<Game?> = listOf())
data class GenreUiState (  val genreList: List<Genre> = listOf() )

fun upDateBadge(notPlayedL: List<Game?>): Int{
    // BottomBarScreen.Pantalla1.badgeCount = notPlayedL.size
    return notPlayedL.size
}