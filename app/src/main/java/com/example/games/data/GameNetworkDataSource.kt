package com.example.games.data

import com.example.games.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GameNetworkDataSource {
    suspend fun getGames() : ArrayList<Game>
   { return withContext(Dispatchers.IO){
            ArrayList()
        }
    }
}
