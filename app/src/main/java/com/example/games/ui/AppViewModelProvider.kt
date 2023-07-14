package com.example.games.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.games.DetailsViewModel
import com.example.games.GameApplication
import com.example.games.MainActivityViewModel
import com.example.games.SearchViewModel


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
                inventoryApplication().container.itemsRepository,
                inventoryApplication().container.gameRepository,

            )
        }
        /**initializer {
            SettingsViewModel(
                inventoryApplication().container.userDataRepository,
                //inventoryApplication().container
                )
        }*/

        initializer {
            MainActivityViewModel(
                inventoryApplication().container.userDataRepository
            )
        }

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