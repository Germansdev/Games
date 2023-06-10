package com.example.games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.games.ui.GameApp
import com.example.games.ui.theme.GamesTheme

//dev1
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Games_Splash)

        super.onCreate(savedInstanceState)

        setContent {


            GamesTheme {
                GameApp()
                }

            }
        }
    }

//dev3 eliminate preview
