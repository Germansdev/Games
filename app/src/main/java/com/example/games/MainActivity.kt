package com.example.games

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import com.example.games.MainActivityUiState.*
import com.example.games.ui.GameApp
import com.example.games.ui.theme.GamesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.InternalCoroutinesApi


//dev1


class MainActivity : ComponentActivity() {

 /**   val viewModel: MainActivityViewModel by viewModels {
        viewModelFactory { MainActivityUiState.Loading } }*/
//
    private val mainViewModel: MainActivityViewModel by viewModels()

    /**
     *   original :
     */

 //   private lateinit var viewModel: SettingsViewModel


    @OptIn(InternalCoroutinesApi::class, ExperimentalLayoutApi::class,
        ExperimentalMaterial3WindowSizeClassApi::class
    )
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Games_Splash)

        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(Loading)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            //with androidApp:
            val systemUiController = rememberSystemUiController()
            val darkTheme = shouldUseDarkTheme(uiState)//com.example.games.setUIMode(uiState,true)//,shouldUseDarkTheme(uiState)//com.example.games.setUIMode(isChecked = false,uiState = uiState)

           // val appContainer = (application as GameApplication).container
           // val notPlayedBadgeViewModel = NotPlayedBadgeViewModel(appContainer.itemsRepository)

            // Update the dark content of the system bars to match the theme
            DisposableEffect(systemUiController, darkTheme) {
                systemUiController.systemBarsDarkContentEnabled = !darkTheme
                onDispose {}
            }


            CompositionLocalProvider() {
                GamesTheme(

                    darkTheme = darkTheme,
                ) {

                    val windowSize = calculateWindowSizeClass(this)


                    GameApp(
                        windowSizeClass = calculateWindowSizeClass(this),
                        )

                }
            }
            Log.d("main_activity", uiState.toString() )
        }
          }

    }





@Composable
private fun shouldUseDarkTheme(
    uiState: MainActivityUiState,
    //viewModel: ThemeViewModel
    ): Boolean = when (uiState) {
    Loading -> isSystemInDarkTheme()
    is Success -> true
    }
