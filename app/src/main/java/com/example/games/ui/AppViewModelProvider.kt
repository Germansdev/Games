package com.example.games.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.games.GameApplication


/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {



        // Initializer for HomeViewModel
        initializer {
            GameViewModel(inventoryApplication().container.gameRepository,
                inventoryApplication().container.itemsRepository)
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [InventoryApplication].
 */
fun CreationExtras.inventoryApplication(): GameApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GameApplication)
