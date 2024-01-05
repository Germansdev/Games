package com.example.games.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.games.R
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.FavoritesViewModel
import com.example.games.ui.GameViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import kotlinx.coroutines.launch

private const val TAG: String = "favorites"
private const val Rate: String = " rate"

@RequiresApi(34)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val favoritesUiState = viewModel.favoritesUiState.collectAsState()

    FavoritesScreenContent(
        favoritesL = favoritesUiState.value.favoritesL as List<Game>,
        modifier = Modifier,
    )
}

@RequiresApi(34)
@Composable
fun FavoritesScreenContent(
    favoritesL: List<Game>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
    ) {

        Row(
            modifier = Modifier
                .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background)
                .align(Alignment.CenterHorizontally)
        ) { }
        Log.e(TAG, favoritesL.size.toString())


        if (favoritesL.isEmpty()) {
            androidx.compose.material.Text(
                text = stringResource(R.string.no_item_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(170.dp),
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),
            ) {
                items(
                    items = favoritesL,
                    key = { game -> game.id }
                ) { game ->

                    GameCardFavorites(
                        game = game.copy(
                            favorited = 1,
                            //isFavorite = true
                        ),
                        modifier
                    )
                }
            }
        }
    }
}

//Log.d(TAG, favoritesL.size.toString()


@RequiresApi(34)
@SuppressLint("AutoboxingStateValueProperty")
@Composable
fun GameCardFavorites(
    game: Game,
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    var play by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }


    ElevatedCard(
        modifier = modifier
            .padding(top = 0.dp, start = 8.dp, end = 8.dp, bottom = 4.dp)
            .fillMaxSize()
            .height(350.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(8.dp),

        ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(.8f)
                    .fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(game.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )

            Column() {
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = game.title,
                        style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FavoriteButton(

                        favorite =
                        when (game.isFavorite) {
                            true -> true
                            else -> false
                        },
                        onFavoriteClick = {
                            gameViewModel.selectFavorite(gameId = game.id)

                            gameViewModel.isFavorite(gameId = game.id)

                            coroutineScope.launch {
                                if (game.isFavorite)
                                    (
                                            gameViewModel.isFavoriteGame(game.copy(isFavorite = false))
                                            ) else (
                                        gameViewModel.isFavoriteGame(game.copy(isFavorite = true))
                                        )
                            }

                        },
                    )

                    val context = LocalContext.current
                    PlayButton(
                        //
                        play = play,
                        onPlayClick = {
                            gameViewModel.selectPlayed(gameId = game.id)

                            gameViewModel.isPlay(gameId = game.id)

                            coroutineScope.launch {

                                (gameViewModel.isPlayedGame(game.copy(isPlayed = true)))

                                playGame(context, game = game)
                            }
                            play = !play
                        },
                    )


                    ShareButton(

                        share = when (game.isShared) {
                            true -> true
                            false -> false
                        },
                        onShareClick = {
                            val link = game.freetogame_profile_url
                            shareGame(
                                context,
                                link = link,
                            )

                            gameViewModel.selectShared(gameId = game.id)

                            gameViewModel.isShare(gameId = game.id)

                            coroutineScope.launch {

                                (gameViewModel.isSharedGame(game.copy(isShared = true)))
                            }



                        }
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(1.dp)
                ) {

                    val selectedRating = remember { mutableStateOf(game.rating) }
                    Log.e(Rate, game.rating.toString())
                    RatingBar(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(CenterVertically),
                        value = selectedRating.value,//rating,
                        config = RatingBarConfig()
                            .padding(2.dp)
                            .size(22.dp)
                            .activeColor(colorResource(id = R.color.orange_star))
                            .inactiveColor(Color.LightGray),
                        onValueChange = { selectedRating.value = it },
                        onRatingChanged = {

                            gameViewModel.selectRate(gameId = game.id)

                            gameViewModel.isRate(gameId = game.id)

                            coroutineScope.launch {
                                gameViewModel.updateRating(game.copy(rating = selectedRating.value))
                            }
                        }
                    )
                }
            }
        }
    }
}