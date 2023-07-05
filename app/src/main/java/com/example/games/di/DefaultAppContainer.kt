package com.example.games.di

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.games.data.DefaultGameRepository
import com.example.games.data.GameDao
import com.example.games.data.GameDatabase
import com.example.games.data.GameRepository
import com.example.games.data.ItemsRepository
import com.example.games.data.OfflineItemsRepository
import com.example.games.model.Game
import com.example.games.network.GameApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//this single line previous, only fetch:
//class DefaultAppContainer: AppContainer {

//this single line with database (include context as constructor of the class):
class DefaultAppContainer(
    private val context: Context,
    //override val database: GameDatabase,

    ) : AppContainer {

    //fetch data from API REST:
    override val gameApiService: GameApiService by lazy {

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GameApiService.BASE_URL)
            .build()
            .create()

    }


    //this with ItemRepository database:
    override val itemsRepository: ItemsRepository by lazy {

        OfflineItemsRepository(
            GameDatabase.getDatabase(context).itemDao(),
            apiService = gameApiService
        )
    }


//this only fetch:

    override val gameRepository: GameRepository by lazy {
        DefaultGameRepository(gameApiService)

    }

//26/06 replace this:
  /**  //yo brandan:
    suspend fun getItems(): ArrayList<Game> {

        var result = gameApiService.getGames()
        updateLocalGames(result)
        return result
    }*/
/**
    //26/06 the previous line with this (from gameViewModel):
    suspend fun getItems() {
        //viewModelScope.launch {
            itemsRepository.insertAll(gameRepository.getGames())
        //}
    }*/

/**
    //Yo added these 2 fun: Plants example:
    fun getLocalGames(): GameDao {
        return GameDatabase.getDatabase(context).itemDao()
    }
    */

/**
    suspend fun updateLocalGames(
        result: ArrayList<Game>
        /**games: ArrayList<Game>?*/
    ) {
        try {
            /**games*/
            result?.let {
                val itemDao = getLocalGames()
                itemDao.insertAll(result)
            }
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "error saving games fetched ${e.message}")
        }
    }*/


}


