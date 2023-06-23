package com.example.games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import com.example.games.ui.GameApp
import com.example.games.ui.GameViewModel
import com.example.games.ui.theme.GamesTheme

//dev1
class MainActivity : ComponentActivity() {
//this line is ok ok ok !! resolved navigation problem clas viewModel!!
   private val viewModel: GameViewModel by viewModels { GameViewModel.Factory }

    //XXXXXX replace with viewModelProvider following codelab:
    //private val viewModel: GameViewModel by viewModels { viewModelFactory {  } }
    /**private val factory = viewModelFactory {
        initializer { GameViewModel(this[]) }
    }
    val viewModel: GameViewModel = factory.create(GameViewModel::class.java)
    */
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_Games_Splash)

        super.onCreate(savedInstanceState)

        setContent {

            GamesTheme {
                GameApp(
                    modifier = Modifier,
                    viewModel = viewModel,
                )
            }

        }
    }
}

//dev3 eliminate preview
