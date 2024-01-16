package com.example.games.ui


import android.app.Application
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.games.DetailsViewModel
import com.example.games.GameApplication
import com.example.games.StatsViewModel
import com.example.games.search.SearchViewModel
import com.example.games.ui.badges.FavoritesBadgeViewModel
import com.example.games.ui.badges.NotPlayedBadgeViewModel
import com.example.games.ui.badges.PlayedBadgeViewModel
import com.example.games.ui.badges.SharedBadgeViewModel
import com.example.games.ui.badges.StatsBadgeViewModel



/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    @RequiresApi(34)
    val Factory = viewModelFactory {

        initializer {
            GameViewModel(
                gameApplication().container.itemsRepository,
                gameApplication().container.gameNetworkDataSource,
                gameApplication().container.status
                )
        }

        initializer {
            FavoritesViewModel( gameApplication().container.itemsRepository)
        }

        initializer {
            PlayedViewModel( gameApplication().container.itemsRepository)
        }

        initializer {
            SharedViewModel( gameApplication().container.itemsRepository)
        }

        initializer {
            NotPlayedViewModel( gameApplication().container.itemsRepository)
        }

        initializer {
            DetailsViewModel(
                gameApplication().container.itemsRepository,
                this.createSavedStateHandle(),
            )
        }

        initializer {
            SearchViewModel(gameApplication().container.itemsRepository)
        }

        initializer {
            StatsViewModel( gameApplication().container.itemsRepository)
        }

        initializer {
            ListedCategoryViewModel(
                gameApplication().container.itemsRepository,
                this.createSavedStateHandle()
            )
        }

        initializer {
            ListedCategoryPlayedViewModel(
                gameApplication().container.itemsRepository,
                this.createSavedStateHandle()
            )
        }

        /**INITIALIZER BADGES:*/
        initializer {
            NotPlayedBadgeViewModel(gameApplication().container.itemsRepository)
        }

        initializer {
            PlayedBadgeViewModel(gameApplication().container.itemsRepository)
        }

        initializer {
           FavoritesBadgeViewModel(gameApplication().container.itemsRepository)
        }

        initializer {
            StatsBadgeViewModel(gameApplication().container.itemsRepository)
        }

        initializer {
            SharedBadgeViewModel(gameApplication().container.itemsRepository)
        }
    }
}

/**Extension function to queries for [Application] object and returns an instance of
 * [GameApplication].
 */
fun CreationExtras.gameApplication(): GameApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)
