package com.example.games.data

import com.example.games.model.Game
import com.example.games.model.NetworkGame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//this replaced with (ItemsRpository, OffLineItemsRepository)

interface GameNetworkDataSource {
    //19/12: anulo esta y reemplazo por la de abajo:
    suspend fun getGames() : ArrayList<Game>
   { return withContext(Dispatchers.IO){
            ArrayList()
        }
       // return listOf()
    }

//suspend fun getGames(ids:List<String>?=null): List<NetworkGame>

}
