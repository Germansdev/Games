package com.example.games.di


import com.example.games.data.GameNetworkDataSource
import com.example.games.data.ItemsRepository
import com.example.games.util.ConnectivityObserver
import com.example.games.network.GameApiService


interface AppContainer {
    val gameApiService: GameApiService  // Interface describes HTTP Rest with Retrofit
    val gameNetworkDataSource: GameNetworkDataSource //Interface describes DataSource from Network
    val itemsRepository: ItemsRepository    //Interface describes DataSource from Local Source Room
    var status: ConnectivityObserver.Status // Interface describes connection state
}

