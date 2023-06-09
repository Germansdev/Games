package com.example.games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.games.ui.GameApp
import com.example.games.ui.theme.GamesTheme

//dev1
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
@Preview
@Composable
fun GameAppPreview(){
   // GameApp()
}
