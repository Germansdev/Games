package com.example.games.data

import com.example.games.model.Game
import com.example.games.network.GameApiService

//this replaced with (ItemsRpository, OffLineItemsRepository)

interface GameRepository {
    suspend fun getGames(): ArrayList<Game>

}
