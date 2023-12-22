package com.example.games


import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.games.MainActivityUiState.*
import com.example.games.data.util.ConnectivityObserver
import com.example.games.data.util.NetworkConnectivityObserver
import com.example.games.model.DarkThemeConfig
import com.example.games.model.ThemeBrand
import com.example.games.model.UserPreferences
import com.example.games.model.UserPreferencesRepository
import com.example.games.ui.GamesApp
import com.example.games.ui.SettingsViewModel
import com.example.games.ui.SettingsViewModelFactory
import com.example.games.ui.theme.GamesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController



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

 private lateinit var connectivityObserver: ConnectivityObserver

    private val mainViewModel: MainActivityViewModel by viewModels()

    private lateinit var viewModel: SettingsViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(
        ExperimentalLayoutApi::class,
        ExperimentalMaterial3WindowSizeClassApi::class
    )
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Games_Splash)

        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        var uiState: MainActivityUiState by mutableStateOf(Loading)

        viewModel = ViewModelProvider(
            this,
            SettingsViewModelFactory(
                UserPreferencesRepository(dataStore)
            )
        ).get(SettingsViewModel::class.java)

        viewModel.initialSetupEvent.observe(this){ initialSetupEvent ->
               UserPreferences(
                   darkThemeConfig = DarkThemeConfig.DARK,
                   gradientColors = false,
                   themeBrand = ThemeBrand.MY_BRAND,
                   isNightMode = true
               )

        }

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {

            val systemUiController = rememberSystemUiController()
            val  darkTheme = shouldUseDarkTheme(uiState /**, viewModel()*/)

            DisposableEffect(systemUiController, /**themeViewModel.theme*/darkTheme) {
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
                    GamesApp(
                        windowSizeClass = calculateWindowSizeClass(this),
                        //networkStatus = NetworkStatus, //implement hilt
                        )

                    val status by connectivityObserver.observe().collectAsState(
                        initial = ConnectivityObserver.Status.Unavailable )

                    Card(
                        border = BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(25.dp),
                        colors = cardColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(top = 650.dp, start = 70.dp, end = 70.dp, bottom = 10.dp)
                           // .wrapContentSize(unbounded = true)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(25.dp))
                            .background(Color.Black)
                            .border(1.dp, color= Color.White),

                    ) {
                        val notConnectedMessage = stringResource(id = R.string.not_connected)
                        if (status!=ConnectivityObserver.Status.Lost)
                        {
                            /**
                            Text(modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.CenterHorizontally),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            text = "Network status: $status")
                            */
                        }else{
                            Text(
                                modifier = Modifier
                                    .padding(12.dp)
                                    .align(Alignment.CenterHorizontally),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                text = stringResource(id = R.string.not_connected))
                             }
                       }
                }
            }
        }
            Log.d("main_activity", uiState.toString() )
    }
}

  //  }

@Composable
private fun shouldUseDarkTheme(
    uiState: MainActivityUiState,
    //viewModel: ThemeViewModel
    ): Boolean = when (uiState) {   //change this is different by dataStore != Nia
    Loading -> isSystemInDarkTheme()
    is Success -> /**true*/when(uiState.userData.darkThemeConfig){
        DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        DarkThemeConfig.LIGHT -> false
        DarkThemeConfig.DARK -> true
    }
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
        ThemeBrand.MY_BRAND -> true
    }
}


/**
 * Connectivity:
 */
/**
@Composable
fun CheckConnectivityStatus() {
    val connection by connectivityStatus()

    val isConnected = connection === ConnectionStatus.Available
    if (isConnected)
    {    }
    else
    {
        Text(text = "No connection GERMAN")}
}

@Composable
fun connectivityStatus(): State<ConnectionStatus>{
    val mCtx = LocalContext.current
    return produceState(initialValue = mCtx.currentConnectionStatus){
        mCtx.observeConnectivityasFlow().collect {value = it}
    }
}

*/