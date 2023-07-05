package com.example.games.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.games.R
import com.example.games.model.Game
import com.example.games.ui.screens.GameCardComonContent

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
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(modifier = Modifier
            .padding(8.dp)
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background),
        ) {
            androidx.compose.material3.Text(
                text = "YOU SHARED THESE GAMES:",
                color = androidx.compose.material3.MaterialTheme.colorScheme.inverseSurface,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))

        if (sharedL.isEmpty()) {
            Text(
                text = stringResource(R.string.no_item_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)

            ) {
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