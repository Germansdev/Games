package com.example.games.data

import com.example.games.model.Game
import com.example.games.network.GameApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

//this replaced with (ItemsRpository, OffLineItemsRepository)

interface GameRepository {
    suspend fun getGames() : ArrayList<Game> /**{
        return withContext(Dispatchers.IO){
            ArrayList()
        }
    }*/

 //   suspend fun getCategories(): List<Game>

  //  suspend fun getShooterGames(): List<Game>
}
