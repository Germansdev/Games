package com.example.games

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.games.ui.DarkThemeConfig
import com.example.games.ui.GameApp
import com.example.games.ui.theme.GamesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//dev1
class MainActivity : ComponentActivity() {

//this line is ok ok ok !! resolved navigation problem clas viewModel!!
    // private val viewModel: GameViewModel by viewModels { GameViewModel.Factory }

    //XXXXXX replace with viewModelProvider following codelab:
    //private val viewModel: GameViewModel by viewModels { viewModelFactory {  } }
    /**private val factory = viewModelFactory {
    initializer { GameViewModel(this[]) }
    }
    val viewModel: GameViewModel = factory.create(GameViewModel::class.java)
     */

    val viewModel: MainActivityViewModel by viewModels()

    //val viewModel: MainActivityViewModel by viewModels{ viewModelFactory {MainActivityUiState.Loading } }
    //private val factory = viewModelFactory { initializer { MainActivityViewModel(this[(key)!!]) } }
    //val viewModel:MainActivityViewModel = factory.create(MainActivityViewModel::class.java)
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Games_Splash)

        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        /** lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState
        .onEach {
        uiState = it
        }
        .collect(collector = {MainActivityUiState.Loading } )
        }
        }*/

        /**
        splashScreen.setKeepOnScreenCondition {
        when (uiState) {
        MainActivityUiState.Loading -> true
        is MainActivityUiState.Success -> false
        }
        }*/
        //with sharePreferences:
        val sharedPreferences = getSharedPreferences("", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val nightMode = sharedPreferences.getBoolean("night", false)

        if(nightMode){

        }else{
          //  !darkTheme
        }

        setContent {
            //with androidApp:
            val systemUiController = rememberSystemUiController()
            val darkTheme = shouldUseDarkTheme(uiState)




            // Update the dark content of the system bars to match the theme
            DisposableEffect(systemUiController, darkTheme) {
                systemUiController.systemBarsDarkContentEnabled = !darkTheme
                onDispose {}
            }
            CompositionLocalProvider() {
                GamesTheme(
                    darkTheme = darkTheme,
                ) {
                    GameApp()
                }


            }
        }
    }

    /**
     * Returns `true` if dark theme should be used, as a function of the [uiState] and the
     * current system context.
     */
    @Composable
    private fun shouldUseDarkTheme(
        uiState: MainActivityUiState,
    ): Boolean = when (uiState) {
        MainActivityUiState.Loading -> isSystemInDarkTheme()
        is MainActivityUiState.Success -> when (uiState.userData.darkThemeConfig) {
            DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
            DarkThemeConfig.LIGHT -> false
            DarkThemeConfig.DARK -> true
        }
    }
}


/**
/**
 * Class summarizing user interest data
 */
data class UserData(
   // val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
    //val useDynamicColor: Boolean,

){
    private val userData : UserData = UserData(
        //themeBrand = ThemeBrand.ANDROID,
        darkThemeConfig = DarkThemeConfig.DARK,
        //useDynamicColor = false,
    )

}





object ParameterData{
    private val userData : UserData = UserData(
       // themeBrand = ThemeBrand.ANDROID,
        darkThemeConfig = DarkThemeConfig.DARK,
      //  useDynamicColor = false,
    )

}


//dev3 eliminate preview
*/

//Shared Preferences:
