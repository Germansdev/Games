package com.example.games

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.games.di.AppContainer
import com.example.games.di.DefaultAppContainer


//class GameApplication: Application() {
    class GameApplication: Application(){
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this,
            //database = GameDatabase.getDatabase(applicationContext)
        )
    }
}