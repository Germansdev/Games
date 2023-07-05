package com.example.games

import android.app.Application
import com.example.games.data.GameDatabase
import com.example.games.di.AppContainer
import com.example.games.di.DefaultAppContainer


//class GameApplication: Application() {
    class GameApplication: Application(){
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this,
            //database = GameDatabase.getDatabase(applicationContext)
        )
    }
}