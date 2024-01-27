package com.example.games


import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.games.ui.GamesApp
import com.example.games.ui.theme.GamesTheme
import com.example.games.util.ConnectivityObserver
import com.example.games.util.NetworkConnectivityObserver

class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

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

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CompositionLocalProvider() {
                GamesTheme {
                    GamesApp(
                        windowSizeClass = calculateWindowSizeClass(this),

                    )
                        val status by connectivityObserver.observe().collectAsState(
                            initial = ConnectivityObserver.Status.Unavailable
                        )

                    val state = remember {
                        MutableTransitionState(false).apply {
                            // Start the animation immediately.
                            targetState = true
                        }
                    }

                    AnimatedVisibility(
                        visibleState = state,
                        enter = fadeIn(animationSpec = tween(1000, 1000))
                                //      + expandHorizontally( animationSpec = tween(1000, 0) )
                                + (slideInHorizontally(animationSpec = tween(1000, 0))),
                        exit = fadeOut(animationSpec = tween(3000, 1500))
                                + shrinkHorizontally(animationSpec = tween(1000, 0)),
                        label = "slideHorizontally connection message"

                    ){
                    Card(
                        border = BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(25.dp),
                        colors = cardColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .padding(top = 600.dp, start = 25.dp, end = 25.dp, bottom = 100.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(25.dp))
                            .background(Color.Black)
                            .border(1.dp, color = Color.White),

                        ) {

                        if (status == ConnectivityObserver.Status.Unavailable) {

                            Text(
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp, start = 4.dp, end = 4.dp)
                                    .align(Alignment.CenterHorizontally)
                                        ,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                text = stringResource(id = R.string.not_connected)//notConnectedMessage
                            )

                        } else if (status == ConnectivityObserver.Status.Lost) {
                            Text(
                                modifier = Modifier
                                    .padding(top = 10.dp, bottom = 10.dp, start = 4.dp, end = 4.dp)
                                    .align(Alignment.CenterHorizontally)
                                        ,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                text = stringResource(id = R.string.not_connected)//notConnectedMessage
                            )
                        }
                    }
                }
            }
        }
    }
}
}
