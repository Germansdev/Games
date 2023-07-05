package com.example.games.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
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
import kotlinx.coroutines.launch

private const val TAG: String = "favorites"

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

@Composable
fun FavoritesScreenContent(
    favoritesL: List<Game>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
            ) {

            Text(
                text = "YOUR FAVORITES GAMES:",
                color = androidx.compose.material3.MaterialTheme.colorScheme.inverseSurface,//MaterialTheme.colors.onSurface,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
           Log.e(TAG, favoritesL.size.toString())

        if (favoritesL.isEmpty()) {
            androidx.compose.material.Text(
                text = stringResource(R.string.no_item_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
           LazyVerticalGrid(
               columns = GridCells.Adaptive(180.dp),
               modifier = modifier.fillMaxWidth(),
               verticalArrangement = Arrangement.spacedBy(8.dp),
               contentPadding = PaddingValues(8.dp),
           ){
               items(
                   items = favoritesL,
                   key = { game -> game.id }
               ) { game ->
                   GameCardFavorites(
                       game = game.copy(isFavorite = true),
                        modifier,
                   )
               }
           }
        }
    }
    Log.d(TAG, favoritesL.size.toString() )
}

@Composable
fun GameCardFavorites(
    game: Game,
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(factory=AppViewModelProvider.Factory),
) {
    var play by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val favorite by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
            .height(350.dp),
        //.aspectRatio(1f),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(8.dp),

    ) {
        //GameCardComonContent(game)
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
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
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
                            //favorite = favorite,
                            favorite =
                            when (game.isFavorite) {
                                true -> true
                                false -> false
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
                                            //gameViewModel.isFavoriteGame(game.copy(favorited = 1))
                                            )
                                    //gameViewModel.isFavoriteGame(game.copy(isFavorite = true))
                                    //gameViewModel.isFavoriteGame(game.copy(favorited = 1))
                                }
                                //favorite = !favorite
                                // favorite! = favorite
                            },
                        )

                        val context = LocalContext.current
                        PlayButton(
                            //
                            play = play,
                            /**play = when (game.isPlayed) {
                            true -> true
                            false -> false
                            },*/
                            onPlayClick = {
                                gameViewModel.selectPlayed(gameId = game.id)

                                gameViewModel.isPlay(gameId = game.id)

                                coroutineScope.launch {

                                    (gameViewModel.isPlayedGame(game.copy(isPlayed = true)))
                                    //(gameViewModel.isPlayedGame(game.copy(played = 1)))

                                    playGame(context, game = game)
                                }
                                play = !play
                            },
                        )
                        //playGame(context, game)

                        ShareButton(
                            // share = share,
                            share = when (game.isShared) {
                                true -> true
                                false -> false
                            },
                            onShareClick = {
                                gameViewModel.selectShared(gameId = game.id)

                                gameViewModel.isShare(gameId = game.id)

                                coroutineScope.launch {

                                    (gameViewModel.isSharedGame(game.copy(isShared = true)))
                                }
                                val subject = ""
                                val summary = ""
                                val link = ""

                                shareGame(
                                    context,
                                    subject = subject,
                                    summary,
                                    link,
                                    game = game
                                )
                                //share = !share
                            }
                        )
                    }
                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)

                                //.padding(bottom = 1.dp)
                                .fillMaxWidth(),

                            horizontalArrangement = Arrangement.spacedBy(1.dp)


                                //.align(Alignment.Start)

                        ) {
                            val selectedRating = remember { mutableStateOf(game.rating) }
                            for (i in 0 until 5) {
                                val icon = if (i < selectedRating.value.toInt()) {
                                    Icons.Filled.StarRate

                                } else {
                                    Icons.Outlined.StarBorder
                                }

                                IconButton(

                                    onClick = {
                                        selectedRating.value = (i + 1).toFloat()
                                        game.rating = selectedRating.value
                                        //to update db:
                                        coroutineScope.launch {

                                            (gameViewModel.isRating(game.copy(rating = selectedRating.value)))

                                        }
                                    }
                                ) {
                                    Icon(
                                        icon,
                                        tint = if (i < selectedRating.value.toInt()) {
                                            colorResource(id = R.color.orange_star)

                                        } else {
                                            Color.Gray
                                        },
                                        contentDescription = null,

                                    )
                                }
                            }
                        }
                    }
                }
            }
        }









