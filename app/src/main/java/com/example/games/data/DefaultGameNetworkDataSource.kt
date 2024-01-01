package com.example.games.data

import com.example.games.model.Game
import com.example.games.network.GameApiService

class DefaultGameNetworkDataSource(
    private val  gameApiService: GameApiService

): GameNetworkDataSource {
     override suspend fun getGames(): ArrayList<Game> {
    return ArrayList()
    }
}