package com.example.games


import android.content.Context
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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.games.MainActivityUiState.*
import com.example.games.model.ThemeBrand
import com.example.games.ui.GamesApp
import com.example.games.ui.theme.GamesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.InternalCoroutinesApi


//dev1

private const val USER_PREFERENCES_NAME = "user_preferences"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = USER_PREFERENCES_NAME,
    produceMigrations = { context ->
        // Since we're migrating from SharedPreferences, add a migration based on the
        // SharedPreferences name
        listOf(SharedPreferencesMigration(context, USER_PREFERENCES_NAME))
    }
)

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
            val  darkTheme = shouldUseDarkTheme(uiState /**, viewModel()*/)//com.example.games.setUIMode(uiState,true)//,shouldUseDarkTheme(uiState)//com.example.games.setUIMode(isChecked = false,uiState = uiState)
 //26 09:           val themeViewModel: ThemeViewModel = viewModel(factory = AppViewModelProvider.Factory)
            //val customTheme =
 //26 09:             themeViewModel.theme.observeAsState(initial = true)
            // Update the dark content of the system bars to match the theme
 /**26 09 */           DisposableEffect(systemUiController, /**themeViewModel.theme*/darkTheme) {
                systemUiController.systemBarsDarkContentEnabled = /**themeViewModel.theme.isInitialized.not() */!darkTheme
                onDispose {}
            }

            CompositionLocalProvider() {


                //CustomTheme(
                GamesTheme(
                    darkTheme = darkTheme,
                    myTheme  = shouldUseNotGradientTheme(uiState),
                    disableDynamicTheming = shouldDisableDynamicTheming(uiState),
                ) {
                    //val windowSize = calculateWindowSizeClass(this)

                    GamesApp(

                        windowSizeClass = calculateWindowSizeClass(this),
                        //windowSize = windowSize.widthSizeClass,
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
    ): Boolean = when (uiState) {   //change this is different by dataStore != Nia
    Loading -> isSystemInDarkTheme()
    is Success -> true
    }


/**
 * Returns `true` if the dynamic color is disabled, as a function of the [uiState].
 */
@Composable
private fun shouldDisableDynamicTheming(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    Loading -> false
    is Success -> !uiState.userData.gradientColors//useDynamicColor
}

@Composable
private fun shouldUseNotGradientTheme(
    uiState: MainActivityUiState,
): Boolean = when (uiState) {
    Loading -> false
    is Success -> when (uiState.userData.themeBrand) {
        ThemeBrand.DEFAULT -> false
        ThemeBrand.MYBRAND -> true
    }
}
