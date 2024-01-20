package com.example.games.ui.screens


import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log.*
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Games
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.Games
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.contentColor
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
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
import com.example.games.GamesNavigationDefaults
import com.example.games.R
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.GameViewModel
import com.example.games.ui.NotPlayedUiState
import com.example.games.ui.NotPlayedViewModel
import kotlinx.coroutines.launch

private const val TAG: String = "Not Played l122"

/***
 * this clas to change color when change rating //Pending: change color through the transition rotation
 */
enum class CardState {
    Start,
    End,
}

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
            .padding(end = 8.dp)

    ) {

        Row(modifier = modifier) {

            MyCardStaticRowNotPlayed()

            MyLazyRowNotPlayed(onGenreClick = onGenreClick)

        }

        d(TAG, notPlayedL.size.toString())

        Spacer(modifier = Modifier.size(8.dp))

        if (notPlayedL.size in 0..300) {
            androidx.compose.animation.AnimatedVisibility(
                visible = notPlayedL.size in 0..300,
                enter = slideInVertically(
                    initialOffsetY = { fullHeight -> -fullHeight },
                ) + fadeIn(),
                exit = slideOutVertically(
                    targetOffsetY = { fullHeight -> -fullHeight },
                ) + fadeOut(),

                ) {
                val loadingContentDescription = "Not Played Screen"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                ) {
                    OverlayLoadingWheel(
                        modifier = Modifier
                            .align(Alignment.Center),
                        contentDesc = loadingContentDescription,
                    )
                }
            }
        }

        val state = remember {
            MutableTransitionState(false).apply {
                // Start the animation immediately.
                targetState = true
            }
        }

        if ((notPlayedL.isEmpty())) {

            AnimatedVisibility(
                visibleState = state,
                modifier.align(CenterHorizontally),
                enter = fadeIn(animationSpec = tween(3000, 1500))
                        //      + expandHorizontally( animationSpec = tween(1000, 0) )
                        + (slideInHorizontally(animationSpec = tween(1000, 0))),
                exit = fadeOut(animationSpec = tween(3000, 1500))
                        + shrinkHorizontally(animationSpec = tween(1000, 0)),
                label = "slideHorizontally connection message"

            ) {

                Column() {
                    Spacer(modifier = Modifier.size(200.dp))
                    Text(
                        modifier = Modifier,
                        fontSize = 26.sp,
                        textAlign = TextAlign.Center,
                        text = "Please: connect to internet,\n" +
                                "close and restart App"
                    )
                }
            }
        } else {

            LazyVerticalGrid(
                columns = GridCells.Adaptive(360.dp),
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp, bottom = 116.dp)
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
        }
    }
}

@RequiresApi(34)
@Composable
fun MyLazyRowNotPlayed(
    onGenreClick: (String) -> Unit,
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
            .padding(end = 12.dp),
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

@RequiresApi(34)
@Composable
fun MyCardStaticRowNotPlayed(
) {
    val notPlayedViewModel: NotPlayedViewModel =
        viewModel(factory = AppViewModelProvider.Factory)

    val notPlayedUiState: State<NotPlayedUiState> =
        notPlayedViewModel.notPlayedUiState.collectAsStateWithLifecycle()

    val notPlayedL = notPlayedUiState.value.notPlayedL

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
                /**whith this solve the problem
                and now could navigate to the other screen
                and changing IntType to StringType Args*/
            }
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

@Composable
fun MyCardRowNotPlayed(
    onGenreClick: (String) -> Unit,
    pair: Pair<String, Int>,
) {

    Modifier.clip(RoundedCornerShape(25.dp))

    var selected by rememberSaveable {
        mutableStateOf(false)
    }

    OutlinedCard(
        border = if (selected) {
            BorderStroke(1.dp, color = GamesNavigationDefaults.navigationSelectedItemColor())
        } else {
            BorderStroke(1.dp, color = if (isSystemInDarkTheme()) Color.White else Color.Black)
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
@SuppressLint("AutoboxingStateValueProperty", "RememberReturnType")
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
                    maxLines = 1,
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

                            gameViewModel.isFavorite(gameId = game.id)

                            coroutineScope.launch {
                                if (game.isFavorite)
                                    (
                                            gameViewModel.isFavoriteGame(
                                                game.copy(
                                                    isFavorite = false,
                                                    favorited = 0
                                                )
                                            )

                                            ) else (

                                        gameViewModel.isFavoriteGame(
                                            game.copy(
                                                isFavorite = true,
                                                favorited = 1
                                            )
                                        )
                                        )
                            }

                        },
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    Box {
                        val context = LocalContext.current

                        PlayButton(

                            play = when (game.isPlayed) {
                                true -> true
                                false -> false
                            },
                            onPlayClick = {

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
                        val link: String = game.freetogame_profile_url

                        ShareButton(
                            share = when (game.isShared) {
                                true -> true
                                false -> false
                            },
                            onShareClick = {
                                shareGame(
                                    context,
                                    link = link,
                                )

                                gameViewModel.isShare(gameId = game.id)

                                coroutineScope.launch {
                                    (gameViewModel.isSharedGame(game.copy(isShared = true)))
                                }
                            },
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    GameOutlinedButton(
                        onClick = { onClick(game) },
                        enabled = true,
                        text = {
                            Text(
                                modifier = Modifier,
                                softWrap = false,
                                fontSize = 12.sp,
                                text = stringResource(R.string.details),
                            )
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))

                Row(
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxSize()

                ) {

                    var active by remember { mutableStateOf(false) }
                    var selectedRating by remember { mutableStateOf(game.rating) }

                    TextButton(
                        modifier = Modifier.width(85.dp),

                        content = {
                            Text(
                                text = if (selectedRating == 0.0f) stringResource(R.string.rate_it) else stringResource(
                                    R.string.to_unrate
                                ),

                                color = if (selectedRating == 0.0f) Color.Green else if (!active && selectedRating > 0.0f) Color.Yellow else Red
                            ).toString()
                        },

                        onClick = {

                            if (!active) coroutineScope.launch {

                                selectedRating = 0.0f

                                gameViewModel.updateRating(game.copy(rating = 0.0f))
                            }
                        }
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    val state = remember {
                        MutableTransitionState(false).apply {
                            // Start the animation immediately.
                            targetState = true
                        }
                    }

                    //  var flag by remember { mutableStateOf(true) }
                    //    var myColor =  remember(flag) { if (flag) R.color.orange_star else R.color.orange_star }

                    val resourceId =
                        when (selectedRating) {
                            1.0f -> R.string.uff
                            2.0f -> R.string.bad
                            3.0f -> R.string.not_than_bad
                            4.0f -> R.string.cool
                            5.0f -> R.string.Great
                            else -> R.string.no
                        }

                    var cardState by remember { mutableStateOf(CardState.Start) }
                    val colorin: Color by animateColorAsState(
                        targetValue =
                        if (cardState == CardState.Start) {
                            Color.White
                        } else {
                            when (selectedRating) {
                                0.0f -> Color.Transparent
                                1.0f -> Color.Red
                                2.0f -> Color.Yellow
                                3.0f -> Color.Cyan
                                4.0f -> Color.Magenta
                                5.0f -> Color.Green
                                else -> Color.Transparent
                            }
                        },
                        label = "colorin"
                    )


                    val rotation = remember { androidx.compose.animation.core.Animatable(0f) }
                    val scope = rememberCoroutineScope()

                    AnimatedVisibility(
                        visibleState = state,
                        enter = fadeIn(animationSpec = tween(3000, 0))
                                //      + expandHorizontally( animationSpec = tween(1000, 0) )
                                + (slideInHorizontally(animationSpec = tween(1000, 0))),
                        exit = fadeOut(animationSpec = tween(3000, 0))
                                + shrinkHorizontally(animationSpec = tween(1000, 0)),
                        label = "slideHorizontally"

                    ) {

                        RatingBar(
                            modifier = Modifier
                                .size(30.dp),
                            rating = selectedRating,
                            starColor = colorResource(id = R.color.orange_star),
                            onRatingChange = {

                                selectedRating = it
                                coroutineScope.launch {

                                    gameViewModel.updateRating(game.copy(rating = selectedRating))

                                }
                                scope.launch {

                                    rotation.animateTo(
                                        targetValue = 180f,
                                        animationSpec = tween(200, easing = LinearEasing),
                                    )

                                    rotation.animateTo(
                                        targetValue = 1080f,
                                        animationSpec = tween(200, easing = LinearEasing),
                                    )

                                    rotation.snapTo(-45f)
                                }

                                cardState = when (cardState) {
                                    CardState.Start -> CardState.End
                                    CardState.End -> CardState.End
                                }
                            },

                            onDismissRequest = {

                                selectedRating = 0.0f

                                coroutineScope.launch {

                                    gameViewModel.updateRating(game.copy(rating = 0.0f))

                                }
                            },
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))


                    Row(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(bottom = 10.dp, top = 10.dp)
                            .rotate(rotation.value),
                        verticalAlignment = CenterVertically,
                        horizontalArrangement = Arrangement.Start,

                        ) {

                        AnimatedVisibility(

                            visibleState = state,

                            enter = fadeIn(animationSpec = tween(4000, 0))
                                    + expandHorizontally(animationSpec = tween(1500, 100)),

                            exit = fadeOut(animationSpec = tween(4000, 100))
                                    + shrinkHorizontally(animationSpec = tween(1500, 100)),
                            label = "expand_rotate"

                        ) {

                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = if (selectedRating == 0.0f) {
                                        Color.Transparent
                                    } else {
                                        Color.Black
                                    },
                                    contentColor = contentColor,
                                ),
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(CircleShape)
                                    .padding(
                                        bottom = 15.dp,
                                        top = 15.dp,
                                        start = 15.dp,
                                        end = 5.dp
                                    )

                            ) {

                                Spacer(modifier = Modifier.size(1.dp))

                                Text(
                                    modifier = Modifier.padding(5.dp),
                                    text = stringResource(id = resourceId),
                                    color = colorin,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 ********Declaration functions ***************
 */


fun playGame(context: Context, game: Game) {
    try {
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
    } catch (e: ActivityNotFoundException) {
        println("Restart app")
    }

}

//INTERNAL FUN TO CREATE INTENT TO SHARE:
fun shareGame(
    context: Context,
    link: String,
) {

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "*/*"
        putExtra(Intent.EXTRA_TEXT, link)
    }
    context.startActivity(
        Intent.createChooser(
            intent,
            context.getString(R.string.newlink)
        )
    )
}

//composable declaration buttons:

@Composable
fun ShareButton(
    share: Boolean,
    onShareClick: () -> Unit,

    ) {

    IconButton(onClick = onShareClick) {
        Icon(
            imageVector = if (share) Icons.Filled.Share else Icons.Outlined.Share,
            contentDescription = null,
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
            tint = if (favorite) Red else Color.LightGray,
            contentDescription = null
        )
    }
}


