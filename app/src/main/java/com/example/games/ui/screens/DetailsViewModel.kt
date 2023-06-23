package com.example.games.ui.screens

import androidx.lifecycle.ViewModelProvider

/**
sealed interface DetailsUiState {
    data class Success(val gameItem: Game) : DetailsUiState
    object Error : DetailsUiState
    object Loading : DetailsUiState
}

class DetailsViewModel(

    private val gameRepository: GameRepository

) : ViewModel() {

    private val _uiStateDetail = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiStateDetail = _uiStateDetail.asStateFlow()

    var selectedGameId by MutableStateOf(" ")

    fun getGame(id: String) {
        viewModelScope.launch {
            _uiStateDetail.value = try {

                val game = gameRepository.getGame(id)
                if (game == null) {
                    DetailsUiState.Error
                } else{
                    DetailsUiState.Success(game)
                }
            } catch (e: IOException) {
                DetailsUiState.Error
            } catch (e: HttpException) {
                DetailsUiState.Error
            }
        }
    }
    /**
     * Factory for BookshelfViewModel] that takes BookshelfRepository] as a dependency
     */

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)
                val gameRepository = application.container.gameRepository
                DetailsViewModel( gameRepository = gameRepository)
            }
        }
    }

}
*/