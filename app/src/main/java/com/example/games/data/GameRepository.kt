package com.example.games.data

import com.example.games.model.Game

//this replaced with (ItemsRpository, OffLineItemsRepository)

interface GameRepository {
    suspend fun getGames(): ArrayList<Game>

}
