package com.example.games.ui.screens


import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.draw.clip
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.games.GamesNavigationDefaults
import com.example.games.R
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.GameViewModel
import com.example.games.ui.PlayedViewModel
import kotlinx.coroutines.launch

private const val TAG: String = "Played"

@RequiresApi(34)
@Composable
fun Played(
    playedViewModel: PlayedViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClick: (Int) -> Unit,
    onGenreClick: (String) -> Unit,
) {

    val playedUiState = playedViewModel.playedUiState.collectAsState()

    PlayedScreenContent(
        playedL = playedUiState.value.playedL as List<Game>,
        modifier = Modifier,
        onClick = {onClick(it.id) },
        onGenreClick = onGenreClick,
    )
}

@RequiresApi(34)
@Composable
fun PlayedScreenContent(
    playedL: List<Game>,
    modifier: Modifier = Modifier,
    onClick: (Game) -> Unit,
    onGenreClick: (String) -> Unit,
) {

    Column(
        modifier = modifier
        //    .padding(bottom = 108.dp)
           // .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row(
            modifier = modifier

        ) {

            MyCardStaticRowPlayed()

            MyLazyRowPlayed(onGenreClick = onGenreClick)

        }
        rememberScaffoldState()

        if (playedL.isEmpty()) {
            androidx.compose.material.Text(
                text = stringResource(R.string.no_games_played),
                color =   if (isSystemInDarkTheme()) {
                    Color.White
                } else {
                    Color.Black
                },
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(300.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(start=8.dp, end=8.dp, bottom = 116.dp)

           /**     columns = GridCells.Adaptive(170.dp),
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp),*/


            ) {
                items(
                    items = playedL,
                    key = { game -> game.id }
                ) { game ->
                   // GameCardPlayed(
                    GameCardColumnNotPlayed(
                   // GameCardPlayed(
                        game = game.copy(isPlayed = true),
                        onClick = onClick,
                       // modifier,
                    )
                }
            }
        }
    }
    Log.d(TAG, playedL.size.toString())
}

@SuppressLint("NewApi")
@Composable
fun MyLazyRowPlayed(
    onGenreClick: (String) -> Unit

) {
    val playedViewModel: PlayedViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val playedUiState = playedViewModel.playedUiState.collectAsState()

    val listed = playedUiState.value.playedL

    val eachSizeGenre = listed.groupingBy { it!!.genre }.eachCount()

    val listEachSizeGenre = eachSizeGenre.toList()

    LazyRow(
        modifier = Modifier
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        items(
            items = listEachSizeGenre
        ) { pair ->

            MyCardRowNotPlayed(
                onGenreClick = onGenreClick,
                pair = pair,
            )
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun MyCardStaticRowPlayed(

) {
    val playedViewModel: PlayedViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val playedUiState = playedViewModel.playedUiState.collectAsState()
    val playedL = playedUiState.value.playedL

    OutlinedCard(
        border = BorderStroke(1.dp, color = GamesNavigationDefaults.navigationSelectedItemColor()),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color.Transparent else GamesNavigationDefaults.navigationIndicatorColor(),
            contentColor = GamesNavigationDefaults.navigationSelectedItemColor()

        ),
        modifier = Modifier
            .padding(start = 16.dp, end = 8.dp)
            .height(60.dp)
            .width(80.dp)
            .clickable {
                /**whith this solve the problem
                and now could navigate to the other screen
                and changing IntType to StringType Args*/
            }
    ) {
        Row(
            Modifier
                .padding(top = 4.dp, bottom = 2.dp, start = 4.dp, end = 4.dp)
                .align(Alignment.CenterHorizontally)
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .align(Alignment.CenterVertically),
                text = "All",
                color = GamesNavigationDefaults.navigationSelectedItemColor(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 10.sp
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 2.dp, bottom = 4.dp)
                .align(Alignment.CenterHorizontally)
                .wrapContentHeight()
        ) {

            Text(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(30.dp)
                    .background(GamesNavigationDefaults.navigationSelectedItemColor())
                    .align(Alignment.CenterVertically),
                text = playedL.size.toString(),
                color =
                if (isSystemInDarkTheme()) {
                    Color.Black
                } else {
                    Color.White
                },
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@SuppressLint("AutoboxingStateValueProperty")
@RequiresApi(34)
@Composable
fun GameCardPlayed(
    game: Game,
    modifier: Modifier,
    gameViewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClick: (Game) -> Unit,
) {

    val play by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    ElevatedCard(
        modifier = modifier
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
            //.padding(top = 0.dp, start = 8.dp, end = 8.dp, bottom = 4.dp)
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

            Column {
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
                            false -> false
                        },
                        onFavoriteClick = {

                            gameViewModel.isFavorite(gameId = game.id)

                            coroutineScope.launch {
                                if (game.isFavorite)
                                    (
                                            gameViewModel.isFavoriteGame(game.copy(isFavorite = false, favorited = 0))
                                            ) else (
                                        gameViewModel.isFavoriteGame(game.copy(isFavorite = true, favorited = 1))
                                        )
                            }
                        },

                        )
                    val context = LocalContext.current
                    PlayButton(
                        play = play,

                        onPlayClick = {

                            gameViewModel.isPlay(gameId = game.id)

                            coroutineScope.launch {

                                (gameViewModel.isPlayedGame(game.copy(isPlayed = true)))

                                playGame(context, game = game)
                            }
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
                                link= link,
                            )

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
                    var selectedRating by remember { mutableStateOf(game.rating)}
                    var active by remember { mutableStateOf(false) }
                   // val selectedRating = remember { mutableStateOf(game.rating) }
                   // MyButton(false, /**onActiveClick = {}*/)
                    RatingBar(
                        modifier = Modifier
                            .size(40.dp),
                        rating = selectedRating,
                        starColor = colorResource(id = R.color.orange_star),
                        onRatingChange = {

                            selectedRating = it
                            coroutineScope.launch {
                                gameViewModel.updateRating(game.copy(rating = selectedRating))
                            }
                        },
                        onDismissRequest = {active = false}
                    )
                }
            }
        }
    }
}