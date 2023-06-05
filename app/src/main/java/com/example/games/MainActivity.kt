package com.example.games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.games.ui.GameApp
import com.example.games.ui.screens.GameListScreen
import com.example.games.ui.theme.GamesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GamesTheme {
                GameApp()
            }
        }
    }
}

