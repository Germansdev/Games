package com.example.games.di


import com.example.games.data.GameRepository
import com.example.games.data.ItemsRepository
import com.example.games.network.GameApiService



interface AppContainer {
    val gameApiService: GameApiService
    val gameRepository: GameRepository

    //using ItemRepository database:
    val itemsRepository: ItemsRepository

  //  val userDataRepository:UserDataRepository
}

