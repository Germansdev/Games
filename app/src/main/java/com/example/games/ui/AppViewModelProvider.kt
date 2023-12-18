package com.example.games.ui


import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.games.DetailsViewModel
import com.example.games.GameApplication
import com.example.games.StatsViewModel
import com.example.games.model.UserPreferencesRepository
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
    val Factory = viewModelFactory {

        // Initializer for HomeViewModel
        initializer {
            GameViewModel(
                inventoryApplication().container.itemsRepository,
                inventoryApplication().container.gameRepository,
                )
        }

        //initializer for FavoritesViewModel
        initializer {
            FavoritesViewModel(
                inventoryApplication().container.itemsRepository,
            )
        }

        initializer {
            PlayedViewModel(
                inventoryApplication().container.itemsRepository,
              //  savedStateHandle = this.createSavedStateHandle()
            )
        }

        initializer {
            SharedViewModel(
                inventoryApplication().container.itemsRepository
            )
        }

        initializer {
            NotPlayedViewModel(
                inventoryApplication().container.itemsRepository,
             //   savedStateHandle = this.createSavedStateHandle()
            )
        }

        initializer {
            DetailsViewModel(
                inventoryApplication().container.itemsRepository,
                this.createSavedStateHandle(),
            )
        }

        initializer {

            SearchViewModel(
                this.createSavedStateHandle(),
                inventoryApplication().container.itemsRepository,
            )
        }

        initializer {
            StatsViewModel(
                inventoryApplication().container.itemsRepository,
            )
        }

        initializer {
            ListedCategoryViewModel(
                inventoryApplication().container.itemsRepository,
                this.createSavedStateHandle()
            )
        }

        initializer {
            ListedCategoryPlayedViewModel(
                inventoryApplication().container.itemsRepository,
                this.createSavedStateHandle()
            )
        }

        /**INITIALIZER BADGES:*/
        initializer {
            NotPlayedBadgeViewModel(
                inventoryApplication().container.itemsRepository
            )
        }

        initializer {
            PlayedBadgeViewModel(
                inventoryApplication().container.itemsRepository
            )
        }

        initializer {
           FavoritesBadgeViewModel(
                inventoryApplication().container.itemsRepository
            )
        }

        initializer {
            StatsBadgeViewModel(
                inventoryApplication().container.itemsRepository
            )
        }

        initializer {
            SharedBadgeViewModel(
                inventoryApplication().container.itemsRepository
            )
        }
 /**       initializer {
            ThemeViewModel()
        }*/

        //INITIALIZER SETTING VIEWMODEL:
    /**    initializer {
            SettingsViewModel(
                inventoryApplication().container.
            )
        }*/
    }
}


/**Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */

fun CreationExtras.inventoryApplication(): GameApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)

/**
fun CreationExtras.inventoryApplication(): InventoryApplication =
(this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApplication)
 */