package com.example.games.di


import com.example.games.data.GameRepository
import com.example.games.data.ItemsRepository
import com.example.games.network.GameApiService
import com.example.games.search.SearchContentsRepository


interface AppContainer {
    val gameApiService: GameApiService
    val gameRepository: GameRepository
   //val searchContentsRepository : SearchContentsRepository
    //using ItemRepository database:
    val itemsRepository: ItemsRepository

   // val userPrefsRepository: UserPrefsRepository
  //  val userDataSourceInt: UserDataSourceInt

}

