package com.example.games.ui.screens


import android.util.Log
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
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import kotlinx.coroutines.launch

private const val TAG: String = "Played"
@Composable
fun Played(
    playedViewModel: PlayedViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onGenreClick: (String) -> Unit,
) {

    val playedUiState = playedViewModel.playedUiState.collectAsState()

    PlayedScreenContent(
        playedL = playedUiState.value.playedL as List<Game>,
        modifier = Modifier,
        onGenreClick = onGenreClick,
    )
}
@Composable
fun PlayedScreenContent(
    playedL: List<Game>,
    modifier: Modifier = Modifier,
    onGenreClick: (String) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

Row( modifier = modifier

) {

    MyCardStaticRowPlayed()

    MyLazyRowPlayed( onGenreClick = onGenreClick)

}

        if (playedL.isEmpty()) {
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
                    items = playedL,
                    key = { game -> game.id }
                ) { game ->
                    GameCardPlayed(
                        game = game.copy(isPlayed = true),
                        modifier,
                    )
                }
            }
        }
    }
    Log.d(TAG, playedL.size.toString() )
}

@Composable
fun MyLazyRowPlayed(
    onGenreClick: (String) -> Unit

) {
    val playedViewModel: PlayedViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val playedUiState = playedViewModel.playedUiState.collectAsState()

    val listed = playedUiState.value.playedL

    val eachSizeGenre = listed.groupingBy { it!!.genre }.eachCount()

    val listEachSizeGenre = eachSizeGenre.toList()

    Row(
        modifier = Modifier
            .height(60.dp)

    ) {

        LazyRow(
            modifier = Modifier,
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
}

@Composable
fun MyCardStaticRowPlayed(

) {
    val playedViewModel: PlayedViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val playedUiState = playedViewModel.playedUiState.collectAsState()
    val playedL= playedUiState.value.playedL

    OutlinedCard(
        border= BorderStroke(1.dp, color= GamesNavigationDefaults.navigationSelectedItemColor()),
        shape = RoundedCornerShape(25.dp),
        colors= CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color.Transparent else GamesNavigationDefaults.navigationIndicatorColor(),
            contentColor = GamesNavigationDefaults.navigationSelectedItemColor()

        ),
        modifier = Modifier
            .height(60.dp)
            .width(80.dp)
            .clickable {
                /**whith this solve the problem
                and now could navigate to the other screen
                and changing IntType to StringType Args*/
            }
    ){
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
                textAlign = TextAlign.Center
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
                if (isSystemInDarkTheme()) {Color.Black} else { Color.White },
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun GameCardPlayed(
    game: Game,
    modifier: Modifier,
    gameViewModel: GameViewModel = viewModel(factory=AppViewModelProvider.Factory),
) {

    var play by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    ElevatedCard(
        modifier = modifier
            .padding(8.dp)
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
                    Row(modifier = Modifier.align(Alignment.CenterHorizontally)
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
                            when(game.isFavorite){
                                true -> true
                                false -> false
                            },
                            onFavoriteClick = {

                                gameViewModel.selectFavorite(gameId = game.id)

                                gameViewModel.isFavorite(gameId = game.id)

                                coroutineScope.launch {
                                    if(game.isFavorite)
                                        (
                                                gameViewModel.isFavoriteGame(game.copy(isFavorite = false))

                                                ) else (
                                            gameViewModel.isFavoriteGame(game.copy(isFavorite = true)                                         )
                                        )

                                }
                            },

                        )
                        val context = LocalContext.current
                        PlayButton(
                            play = play,

                        /**    play = when (game.isPlayed) {
                                true -> true
                                false -> false
                            },*/
                            onPlayClick = {
                                gameViewModel.selectPlayed(gameId = game.id)

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
                                gameViewModel.selectShared(gameId = game.id)

                                gameViewModel.isShare(gameId = game.id)

                                coroutineScope.launch {

                                    (gameViewModel.isSharedGame(game.copy(isShared = true)))
                                }
                                val subject =""
                                val summary =""
                                val link = ""

                                shareGame(
                                    context,
                                    subject = subject,
                                    summary,
                                    link
                                )
                            }
                        )
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)

                            //.padding(bottom = 1.dp)
                            .fillMaxWidth(),

                        horizontalArrangement = Arrangement.spacedBy(1.dp)


                        //.align(Alignment.Start)

                    ) {

                        val selectedRating = remember { mutableStateOf(game.rating) }

                        // var rating: Float by rememberSaveable { mutableStateOf(0f) }
                        RatingBar(
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.CenterVertically)
                            //  .clickable { game.id }
                            ,

                            value = selectedRating.value,//rating,
                            config = RatingBarConfig(
                            )
                                .activeColor(colorResource(id = R.color.orange_star))
                                .inactiveColor(Color.LightGray),

                            onValueChange = { selectedRating.value = it},
                            onRatingChanged = {

                                gameViewModel.selectRate(gameId = game.id)

                                gameViewModel.isRate(gameId = game.id)

                                //to update db:
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