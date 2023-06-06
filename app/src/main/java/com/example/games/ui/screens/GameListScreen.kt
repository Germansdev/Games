package com.example.games.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Games
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.games.ui.GameViewModel

private const val TAG: String = "Dev4"

@Composable
fun GameListScreen(
    modifier: Modifier = Modifier,
    games: List<Game>

) {
   val gameViewModel: GameViewModel = viewModel()

    //val games = gameViewModel.games.value
    val favorites = gameViewModel.favorites.value
    val play = gameViewModel.play.value
    val share = gameViewModel.share.value

    if (games.isEmpty()) {
        NothingFoundScreen()
    } else {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(
            items = games,
            key = { game -> game.title }
        ) { game ->
            GameCard(
               gameViewModel,
                game,
            )
        }
    }
       // Log.d(TAG, listOf(game.title).size.toString())
        Log.d(TAG, games.size.toString())
//val navController = rememberNavController()
}}


@Composable
fun GameCard(

    gameViewModel: GameViewModel,
    game: Game,
    //gameUiState: GameUiState
    /**modifier: Modifier = Modifier */
) {
    //val games = gameViewModel.games.value

    // USING FavoriteViewModel:
    var favorite by remember { mutableStateOf(false) }
    favorite = gameViewModel.isFavorite(gameId = game.id.toString())

    //var rating by remember { mutableStateOf(false) }
   // rating = gameViewModel.

    var play by remember { mutableStateOf(false) }
    play = gameViewModel.isPlay(gameId = game.id.toString())

    var share by remember { mutableStateOf(false) }
    share = gameViewModel.isShare(gameId = game.id.toString())

    val selectedRating = remember { mutableStateOf(game.rating) }

    ElevatedCard(
        modifier = Modifier//modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(game.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )

            Column(modifier = Modifier.padding(8.dp)) {

                Text(
                    text = stringResource(
                        id = R.string.game_title,
                        game.title
                    ),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = game.short_description,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(end = 4.dp, top = 2.dp, bottom = 4.dp)
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    //  Box() {
                    FavoriteButton(
                        favorite = favorite,
                        onFavoriteClick = {
                            gameViewModel.selectFavorite(gameId = game.id.toString())

                            /**
                            if (favorite) {
                                gameViewModel.
                            } else {
                                gameViewModel.addFavoriteGame(game)
                            }
                            favorite = !favorite
                            */
                        }
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                    Box() {
                        PlayButton(
                            play = play,

                            onPlayClick = {
                                gameViewModel.isPlay(gameId = game.id.toString())

                                /**
                                if (play) {

                                }

                                    viewPlayModel.removePlayedGame(game)
                                } else {
                                    viewPlayModel.addPlayedGame(game)
                                }
                                play = !play
                                */
                            }
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Box() {
                        ShareButton(
                            share = share,
                            onShareClick = {
                                gameViewModel.isShare(gameId = game.id.toString())

                                /**
                                if (share) {
                                    shareViewModel.removeSharedGame(game)
                                } else {
                                    shareViewModel.addSharedGame(game)
                                }
                                share = !share
                                */
                            }
                        )
                    }
                }
            }

                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(bottom = 8.dp)
                        .fillMaxSize()

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
                            }
                        ) {
                            Icon(
                                icon,
                                tint = if (i < selectedRating.value.toInt()) {
                                    colorResource(id = R.color.orange_star)
                                } else {
                                    Color.Gray
                                },
                                contentDescription = null
                            )
                        }

                    }
                }

            }
        }
    }

//composable declaration:

@Composable
fun ShareButton(
    share: Boolean,
    onShareClick: () -> Unit
) {
    IconButton(
        onClick = onShareClick
    ) {
        Icon(

            imageVector = if (share) Icons.Filled.Share else Icons.Outlined.Share,
            tint = if (share) Color.Blue else Color.LightGray,
            contentDescription = null
        )
    }
}

@Composable
fun PlayButton(
    play: Boolean,
    onPlayClick: () -> Unit
) {
    IconButton(
        onClick = onPlayClick
    ) {
        Icon(
            imageVector = if (play) Icons.Filled.Games else Icons.Outlined.Games,
            tint = if (play) Color.Green else Color.LightGray,
            contentDescription = null
        )
    }
}

@Composable
fun FavoriteButton(
    favorite: Boolean,
    onFavoriteClick: () -> Unit,
) {
    IconButton(
        onClick = onFavoriteClick
    ) {
        Icon(
            imageVector = if (favorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
            tint = if (favorite) Color.Red else Color.LightGray,
            contentDescription = null
        )
    }
}

/**
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
GamesTheme {
LoadingScreen()
}
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
GamesTheme {
ErrorScreen(modifier = Modifier, retryAction = {})
}
}


@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
GamesTheme() {

val mockData = List(10)

{

Game(

id = 11,
short_description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do" +
" eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad" +
" minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
" ex ea commodo consequat.",
title = "Lorem Ipsum - $it",
thumbnail = " "
)
}
//not preview exception viewModel in preview:
GameListScreen(viewModel(), onClick = {}, games = mockData )

}
}

 */




