package com.example.games.di


import com.example.games.data.GameRepository
import com.example.games.data.ItemsRepository
import com.example.games.network.GameApiService


interface AppContainer {
    val gameApiService: GameApiService
    val gameRepository: GameRepository
    val itemsRepository: ItemsRepository
}

