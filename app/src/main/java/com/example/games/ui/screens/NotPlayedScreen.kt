package com.example.games.ui.screens


 import android.annotation.SuppressLint
 import android.content.Context
 import android.content.Intent
 import android.net.Uri
 import android.util.Log.*
 import androidx.annotation.RequiresApi
 import androidx.compose.foundation.BorderStroke
 import androidx.compose.foundation.background
 import androidx.compose.foundation.clickable
 import androidx.compose.foundation.isSystemInDarkTheme
 import androidx.compose.foundation.layout.*
 import androidx.compose.foundation.lazy.LazyRow
 import androidx.compose.foundation.lazy.grid.GridCells
 import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
 import androidx.compose.foundation.lazy.grid.items
 import androidx.compose.foundation.lazy.items
 import androidx.compose.foundation.shape.CircleShape
 import androidx.compose.foundation.shape.RoundedCornerShape
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
 import androidx.compose.runtime.getValue
 import androidx.compose.runtime.mutableStateOf
 import androidx.compose.runtime.remember
 import androidx.compose.runtime.rememberCoroutineScope
 import androidx.compose.runtime.saveable.rememberSaveable
 import androidx.compose.runtime.setValue
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Alignment.Companion.CenterHorizontally
 import androidx.compose.ui.Alignment.Companion.CenterVertically
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.draw.clip
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.layout.ContentScale
 import androidx.compose.ui.platform.LocalContext
 import androidx.compose.ui.res.colorResource
 import androidx.compose.ui.res.painterResource
 import androidx.compose.ui.text.font.FontWeight
 import androidx.compose.ui.text.style.TextAlign
 import androidx.compose.ui.text.style.TextOverflow
 import androidx.compose.ui.unit.dp
 import androidx.compose.ui.unit.sp
 import androidx.lifecycle.compose.collectAsStateWithLifecycle
 import androidx.lifecycle.viewmodel.compose.viewModel
 import coil.compose.AsyncImage
 import coil.request.ImageRequest
 import com.example.games.GamesNavigationDefaults
 import com.example.games.R
 import com.example.games.model.Game
 import com.example.games.ui.AppViewModelProvider
 import com.example.games.ui.GameViewModel
 import com.example.games.ui.NotPlayedUiState
 import com.example.games.ui.NotPlayedViewModel
 import com.gowtham.ratingbar.RatingBar
 import com.gowtham.ratingbar.RatingBarConfig
 import kotlinx.coroutines.launch


private const val TAG: String = "Not Played l122"



@RequiresApi(34)
@Composable
fun NotPlayedScreen(
    viewModel: NotPlayedViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClick: (Int) -> Unit,
    onGenreClick: (String) -> Unit,

) {

    val notPlayedUiState = viewModel.notPlayedUiState.collectAsStateWithLifecycle()

    NotPlayedScreenContent(
        notPlayedL = notPlayedUiState.value.notPlayedL as List<Game>,
        modifier = Modifier,
        onClick = { onClick(it.id) },
        onGenreClick = onGenreClick,
    )
}

@RequiresApi(34)
@Composable
fun NotPlayedScreenContent(
    notPlayedL: List<Game>,
    modifier: Modifier = Modifier,
    onClick: (Game) -> Unit,
    onGenreClick: (String) -> Unit,
    ) {

    Column(

        modifier = modifier
            .fillMaxSize()

    ) {

    Row(modifier = modifier) {

        MyCardStaticRowNotPlayed()

        MyLazyRowNotPlayed( onGenreClick = onGenreClick)

}

        d(TAG, notPlayedL.size.toString())

        Spacer(modifier = Modifier.size(8.dp))

        if (notPlayedL.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(360.dp/**180.dp*/),
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(
                    items = notPlayedL,
                    key = { game -> game.id }
                ) { game ->
                    GameCardColumnNotPlayed(
                        game = game.copy(isPlayed = false),
                        onClick = onClick,
                    )
                }
            }
        } else {

        /**    androidx.compose.material.Text(
                text = stringResource(R.string.not_connected),
                style = MaterialTheme.typography.subtitle2
            )*/
        }

    }
}


@Composable
    fun MyLazyRowNotPlayed(
    onGenreClick: (String) -> Unit,
    modifier: Modifier = Modifier
    ) {

        val notPlayedViewModel: NotPlayedViewModel =
            viewModel(factory = AppViewModelProvider.Factory)

        val notPlayedUiState: State<NotPlayedUiState> =
            notPlayedViewModel.notPlayedUiState.collectAsStateWithLifecycle()

        val listed = notPlayedUiState.value.notPlayedL

        val eachSizeGenre = listed.groupingBy { it!!.genre }.eachCount()

        //to generate list of pair:
        val listEachSizeGenre = eachSizeGenre.toList()
            .toMutableList()

        listEachSizeGenre.sortByDescending { eachSizeGenre.size.dec() }//does not make any difference

            LazyRow(
                modifier = Modifier
                    .height(60.dp)
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),

                ) {
                items(
                    items = listEachSizeGenre,
                ) { pair ->

                    MyCardRowNotPlayed(
                        onGenreClick = onGenreClick,
                        pair = pair,
                    )
                }
            }
    }

@Composable
fun MyCardStaticRowNotPlayed(
) {
    val notPlayedViewModel: NotPlayedViewModel =
        viewModel(factory = AppViewModelProvider.Factory)

    val notPlayedUiState: State<NotPlayedUiState> =
        notPlayedViewModel.notPlayedUiState.collectAsStateWithLifecycle()

    val notPlayedL= notPlayedUiState.value.notPlayedL

    OutlinedCard(
        border= BorderStroke(1.dp, color= GamesNavigationDefaults.navigationSelectedItemColor()),
        shape = RoundedCornerShape(25.dp),
        colors= CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) Color.Transparent else GamesNavigationDefaults.navigationIndicatorColor(),
            contentColor = GamesNavigationDefaults.navigationSelectedItemColor()

        ),
        modifier = Modifier
            .padding(start = 16.dp,end = 8.dp)
            .height(60.dp)
            .width(80.dp)
            .clickable {
                /**whith this solve the problem
                and now could navigate to the other screen
                and changing IntType to StringType Args*/
                /**whith this solve the problem
                and now could navigate to the other screen
                and changing IntType to StringType Args*/

            }
    ){
        Row(
            Modifier
                .padding(top = 4.dp, bottom = 2.dp, start = 4.dp, end = 4.dp)
                .align(CenterHorizontally)
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .align(CenterVertically),
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
                .align(CenterHorizontally)
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier
                    .clip(CircleShape)
                    .width(30.dp)
                    .background(
                        GamesNavigationDefaults.navigationSelectedItemColor()
                    )
                    .align(CenterVertically),
                text = notPlayedL.size.toString(),
                color =if (isSystemInDarkTheme()) {Color.Black} else { Color.White },
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun MyCardRowNotPlayed(
    onGenreClick: (String) -> Unit,
    pair: Pair<String, Int>,
    modifier: Modifier = Modifier,
) {

    Modifier.clip(RoundedCornerShape(25.dp))

    var selected by rememberSaveable {
    mutableStateOf(false)}
        selected = selected

    OutlinedCard(
        border= if (selected ) {
            BorderStroke(1.dp, color= GamesNavigationDefaults.navigationSelectedItemColor())
        } else {
            BorderStroke(1.dp, color= if (isSystemInDarkTheme()) Color.White else Color.Black)
        },
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .height(60.dp)
            .widthIn(80.dp, 100.dp) 
            .clickable {
                onGenreClick(pair.first)
                selected = !selected

                /**whith this solve the problem
                and now could navigate to the other screen
                and changing IntType to StringType Args*/
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
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp
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

@RequiresApi(34)
@SuppressLint("AutoboxingStateValueProperty")
@Composable
fun GameCardColumnNotPlayed(
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
                        text = {
                            Text( modifier = Modifier,
                            softWrap = false,
                            fontSize = 12.sp,
                            text = "Details",
                            //fontSize = 16.dp,
                        ) },
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