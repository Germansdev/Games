package com.example.games.di

import android.content.Context
import com.example.games.data.DefaultGameRepository
import com.example.games.data.GameDao
import com.example.games.data.GameDatabase
import com.example.games.data.GameRepository
import com.example.games.data.ItemsRepository
import com.example.games.data.OfflineItemsRepository
import com.example.games.network.GameApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//this single line previous, only fetch:
//class DefaultAppContainer: AppContainer {

//this single line with database (include context as constructor of the class):
    class DefaultAppContainer (private val context: Context): AppContainer {

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
        OfflineItemsRepository(GameDatabase.getDatabase(context).itemDao())

    }

//this only fetch:

    override val gameRepository: GameRepository by lazy {
        DefaultGameRepository (gameApiService)
    }


    //yo brandan:



}