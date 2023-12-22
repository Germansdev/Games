package com.example.games.data

import com.example.games.model.Game
import com.example.games.model.NetworkGame
import com.example.games.network.GameApiService

class DefaultGameNetworkDataSource(
    private val  gameApiService: GameApiService

): GameNetworkDataSource {
    //19/12: tapo este override y agrego nuevo abajo:
    /**19/12: */
     override suspend fun getGames(): ArrayList<Game> {
    return ArrayList()//gameApiService.getGames()
    }
 /**   override suspend fun getGames(/**19/12 este parametro:*/ids: List<String>?): ArrayList<NetworkGame> {

        return ArrayList()
    }*/
}