package com.example.games.di

import com.example.games.data.DefaultGameRepository
import com.example.games.data.GameRepository
import com.example.games.network.GameApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class DefaultAppContainer: AppContainer {
    override val gameApiService: GameApiService by lazy {

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GameApiService.BASE_URL)
            .build()
            .create()
    }

    override val gameRepository: GameRepository by lazy {
       DefaultGameRepository (gameApiService)
    }

}