package com.example.games.di


import com.example.games.data.GameNetworkDataSource
import com.example.games.data.ItemsRepository
import com.example.games.data.util.ConnectivityObserver
import com.example.games.network.GameApiService


interface AppContainer {
    val gameApiService: GameApiService
    val gameNetworkDataSource: GameNetworkDataSource
    val itemsRepository: ItemsRepository
    var status: ConnectivityObserver.Status
}

