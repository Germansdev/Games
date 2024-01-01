package com.example.games.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.games.data.DefaultGameNetworkDataSource
import com.example.games.data.GameDatabase
import com.example.games.data.GameNetworkDataSource
import com.example.games.data.ItemsRepository
import com.example.games.data.OfflineItemsRepository
import com.example.games.data.util.ConnectivityObserver
import com.example.games.network.GameApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@RequiresApi(Build.VERSION_CODES.R)
class DefaultAppContainer(

    private val context: Context, override var status: ConnectivityObserver.Status,

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
            apiService = gameApiService,
        )
    }

    //this only fetch:
    override val gameNetworkDataSource: GameNetworkDataSource by lazy {
        DefaultGameNetworkDataSource(gameApiService)
    }

}

