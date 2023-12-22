package com.example.games.ui.screens

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.games.R
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.SharedViewModel

private const val TAG: String = "Shared"
@Composable
fun SharedScreen(
    viewModels: SharedViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val sharedUiState = viewModels.sharedUiState.collectAsState()

    SharedScreenContent(
        sharedL = sharedUiState.value.sharedL as List<Game>,
        modifier = Modifier,
    )
}
@Composable
fun SharedScreenContent(
    sharedL: List<Game>,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
           // .padding(8.dp),
       // verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(modifier = Modifier.size(8.dp))

        if (sharedL.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {

            LazyVerticalGrid(
                columns = GridCells.Adaptive(360.dp),
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),
            )

            {
                items(
                    items = sharedL,
                    key = { game -> game.id }
                ) { game ->
                    GameCardShared(
                        game = game.copy(isShared = true),
                    )
                }
            }
        }
    }
    Log.d(TAG, sharedL.size.toString() )
}


@Composable
fun GameCardShared(
    game: Game,
) {
    //comon content all options bottom bar:
    GameCardComonContent( game)
}

/**
 * To apply a brush with custom function (NOT USED: all Gradient is in theme):
 */
@Composable
fun BrushRes(): Brush {
  if(  isSystemInDarkTheme()) {
      listOf(
          Color.Black,
          Color.Blue
      )
  }else{
      listOf(
          Color.Yellow,
            Color.White
      )
  }
    return BrushRes()
}