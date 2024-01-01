package com.example.games

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.games.data.util.ConnectivityObserver
import com.example.games.di.AppContainer
import com.example.games.di.DefaultAppContainer


    class GameApplication: Application(){
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this,
            status= ConnectivityObserver.Status.Unavailable
        )
    }
}