package com.example.games.ui.screens


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Games
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Games
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.games.R
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.GameViewModel
import com.example.games.ui.HomeUiState
import com.example.games.ui.NotPlayedUiState
import com.example.games.ui.NotPlayedViewModel
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import kotlinx.coroutines.launch


private const val TAG: String = "Not Played"

@Composable
fun NotPlayedScreen(
    viewModel: NotPlayedViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClick: (Int) -> Unit,
    onGenreClick: (String) -> Unit,
 //   gameViewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory)

) {

     val notPlayedUiState = viewModel.notPlayedUiState.collectAsStateWithLifecycle()

    NotPlayedScreenContent(
        notPlayedL = notPlayedUiState.value.notPlayedL as List<Game>,
        modifier = Modifier,
        onClick = { onClick(it.id) },
        onGenreClick = onGenreClick,
    )
}

@Composable
fun NotPlayedScreenContent(
    notPlayedL: List<Game>,
    modifier: Modifier = Modifier,
    onClick: (Game) -> Unit,
    onGenreClick: (String) -> Unit,

) {

    Column(
        modifier = modifier

            .background(
                brush = Brush.verticalGradient(
                    if (isSystemInDarkTheme()) {
                        listOf(
                            Color.Black,
                            Color.Blue
                        )
                    } else {
                        listOf(
                            Color(0xFF1E88E5),
                            Color.White
                        )
                    }
                )
            )
            .fillMaxSize()

    ) {

        MyLazyRow( onGenreClick = onGenreClick )

        d(TAG, notPlayedL.size.toString())

        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                .align(CenterHorizontally)
        ) {

            Text(
                text = "NOT PLAYED GAMES:",
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .background(if (isSystemInDarkTheme()) Color.Black else Color.Yellow)
            )
        }
        Spacer(modifier = Modifier.size(8.dp))

        if (notPlayedL.isEmpty()) {
            androidx.compose.material.Text(
                text = stringResource(R.string.no_item_description),
                style = MaterialTheme.typography.subtitle2
            )
        } else {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)

            ) {
                items(
                    items = notPlayedL,
                    key = { game -> game.id }
                ) { game ->
                    GameCardNotPlayed(
                        game = game.copy(isPlayed = false),
                        onClick = onClick,
                    )
                }
            }
        }
    }
}

@Composable
fun MyLazyRow(
    onGenreClick: (String) -> Unit,
   // gameViewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    val notPlayedViewModel: NotPlayedViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val notPlayedUiState: State<NotPlayedUiState> = notPlayedViewModel.notPlayedUiState.collectAsStateWithLifecycle()

    //val notPlayedL = notPlayedUiState.value.notPlayedL

    val listed = notPlayedUiState.value.notPlayedL

    val eachSizeGenre = listed.groupingBy { it!!.genre }.eachCount()

    //this is only to see in log:
    for (i in eachSizeGenre) {
        println("${eachSizeGenre.values}"  )
    }

    //to generate list of pair:
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

                    MyCard(
                        onGenreClick = onGenreClick,
                        pair = pair,
                    )
                }
            }
        }
    }


@Composable
fun MyCard(
    onGenreClick: (String) -> Unit,
    pair: Pair<String, Int>,
   // viewModel: NotPlayedViewModel = viewModel(factory = AppViewModelProvider.Factory),
    gameViewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier,
) {

    val homeUiState: State<HomeUiState> = gameViewModel.homeUiState.collectAsStateWithLifecycle()
    val listed = homeUiState.value.itemList

    val eachSizeGenre = listed.groupingBy { it!!.genre }.eachCount()
    for (i in eachSizeGenre) {
        println( "${eachSizeGenre.values}" )
    }

    Modifier.clip(RoundedCornerShape(25.dp))

    OutlinedCard(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .height(60.dp)
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .clickable {
                onGenreClick(pair.first)
                /**whith this solve the problem
                and now could navigate to the other screen
                and changing IntType to StringType Args*/
            }

    ) {

        Column(
            modifier = Modifier
                .align(CenterHorizontally)

        ) {

            Row(
                Modifier
                    .padding(top = 4.dp, bottom = 2.dp, start = 4.dp, end = 4.dp)
                    .align(CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .align(CenterVertically),
                    text = pair.first,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 2.dp, bottom = 4.dp)
                    .align(CenterHorizontally)
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .width(30.dp)
                        .background(if (isSystemInDarkTheme()) Color.White else Color.Black)
                        .align(CenterVertically),
                    text = pair.second.toString(),
                    color = if (isSystemInDarkTheme()) {
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
}

@SuppressLint("AutoboxingStateValueProperty")
@Composable
fun GameCardNotPlayed(
    game: Game,
    gameViewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClick: (Game) -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(8.dp),

        ) {

        Column {

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
                    text = game.title,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = game.short_description,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(end = 4.dp, top = 2.dp, bottom = 4.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth()

                ) {

                    FavoriteButton(

                        favorite =
                        when (game.isFavorite) {
                            true -> true
                            false -> false
                        },
                        onFavoriteClick = {
                         //   e(Tag, game.title)
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

                    Spacer(modifier = Modifier.size(16.dp))

                    Box {
                        val context = LocalContext.current
                        PlayButton(
                            //play = play,
                            play = when (game.isPlayed) {
                                true -> true
                                false -> false
                            },
                            onPlayClick = {
                                gameViewModel.selectPlayed(gameId = game.id)

                                gameViewModel.isPlay(gameId = game.id)

                                coroutineScope.launch {

                                    (gameViewModel.isPlayedGame(game.copy(isPlayed = true)))

                                    playGame(context, game = game)
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    Box {
                        val context = LocalContext.current
                        val subject = R.string.subject
                        val summary = "Play this game, is incredible"
                        val link: String = game.game_url
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
                                shareGame(
                                    context,
                                    subject = subject.toString(),
                                    summary,
                                    link
                                )
                            }
                        )
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    GameOutlinedButton(
                        onClick = { onClick(game) },
                        enabled = true,
                        text = { Text(text = "Details") },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.Start)
                        .padding(bottom = 8.dp)
                        .fillMaxSize()

                ) {
                    val selectedRating = remember { mutableStateOf(game.rating) }

                    RatingBar(
                        modifier = Modifier,
                        value = selectedRating.value,//rating,
                        config = RatingBarConfig(
                        )
                            .activeColor(colorResource(id = R.color.orange_star))
                            .inactiveColor(Color.LightGray),

                        onValueChange = { selectedRating.value = it },

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

/**
 ********Declaration functions ***************
 */


fun playGame(context: Context, game: Game) {
    val myIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(game.game_url)
    )
    context.startActivity(
        Intent.createChooser(
            myIntent,
            context.getString(R.string.game_title)
        ),
    )
}

//INTERNAL FUN TO CREATE INTENT TO SHARE:
fun shareGame(
    context: Context, subject: String, summary: String, link: String,
) {

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, subject)
        putExtra(Intent.EXTRA_TEXT, summary)
        putExtra(Intent.EXTRA_TEXT, link)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(
                (R.string.link)
            )
        )
    )
}

//composable declaration buttons:

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
            contentDescription = null,
            //tint = if (share) Color.Red else Color.LightGray,
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.LightGray
        )
    }
}

@Composable
fun PlayButton(
    play: Boolean,
    onPlayClick: () -> Unit,
) {

    IconButton(
        onClick = onPlayClick

    ) {
        Icon(
            imageVector = if (play) Icons.Rounded.Games else Icons.Outlined.Games,
            //tint = if (play) Color.Red else Color.LightGray,
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.LightGray
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
            imageVector = if (favorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            tint = if (favorite) Color.Red else Color.LightGray,
            contentDescription = null
        )
    }
}