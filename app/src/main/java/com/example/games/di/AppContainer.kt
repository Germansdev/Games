package com.example.games.di


import com.example.games.data.GameDatabase
import com.example.games.data.GameRepository
import com.example.games.data.ItemsRepository
import com.example.games.network.GameApiService

interface AppContainer {
    val gameApiService: GameApiService
    val gameRepository: GameRepository

    //using ItemRepository database:
    val itemsRepository: ItemsRepository
//added to see result with favourites screen:
   // val database: GameDatabase
//val patternRepository: PatternRepository
}
/**
class PatternRepository ( gameRepository: GameRepository,itemsRepository: ItemsRepository ): {


}*/