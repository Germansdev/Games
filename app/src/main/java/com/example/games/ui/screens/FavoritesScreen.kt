package com.example.games.ui.screens

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ListItemDefaults
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
import androidx.compose.ui.draw.rotate
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
import com.example.games.R
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.FavoritesViewModel
import com.example.games.ui.GameViewModel
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
            .padding(bottom = 8.dp)
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
                contentPadding = PaddingValues(start=8.dp, end=8.dp, bottom = 108.dp),
            ) {
                items(
                    items = favoritesL,
                    key = { game -> game.id }
                ) { game ->

                    GameCardFavorites(
                        game = game.copy(isFavorite = true),
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

   // val offsetX = remember { mutableStateOf(0f) }
   // val offsetY = remember { mutableStateOf(0f) }

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
                    .fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(game.thumbnail)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
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
                            coroutineScope.launch {
                                gameViewModel.isFavoriteGame(
                                    game.copy(
                                        isFavorite = false,
                                        favorited = 0
                                    )
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

                            gameViewModel.isShare(gameId = game.id)

                            coroutineScope.launch {

                                (gameViewModel.isSharedGame(game.copy(isShared = true)))
                            }
                        }
                    )
                }
                Spacer(modifier = Modifier.size(28.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceBetween//spacedBy(10.dp)

                ) {
                    var active by remember { mutableStateOf(false) }
                    var selectedRating by remember { mutableStateOf(game.rating) }

                    val state = remember {
                        MutableTransitionState(false).apply {
                            // Start the animation immediately.
                            targetState = true
                        }
                    }

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
                       // Spacer(modifier = Modifier.size(28.dp))
                        Row (
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                               // .padding(start = 8.dp)
                                .fillMaxWidth()
                        ){

                            RatingBar(
                                modifier = Modifier
                                   .sizeIn(25.dp , 25.dp, 40.dp, 40.dp),
                                rating = selectedRating,
                                starColor = colorResource(id = R.color.orange_star),
                                onRatingChange = {

                                    selectedRating = it
                                    coroutineScope.launch {
                                        gameViewModel.updateRating(game.copy(rating = selectedRating))

                                    }
                                    scope.launch {

                                        rotation.animateTo(
                                            targetValue = 180f,//180f,//360f,//180f,
                                            animationSpec = tween(200, easing = LinearEasing),
                                            // initialVelocity = 100.0F
                                        )

                                        // flag= !flag

                                        rotation.animateTo(
                                            targetValue = 1080f,//720f,//360f,
                                            animationSpec = tween(200, easing = LinearEasing),
                                            //initialVelocity = 100.0F
                                        )

                                        rotation.snapTo(-45f)
                                    }

                                    cardState = when (cardState) {
                                        CardState.Start -> CardState.End
                                        CardState.End -> CardState.End
                                    }
                                },
                                onDismissRequest = {
                                    // active = false

                                    selectedRating = 0.0f
                                    coroutineScope.launch {

                                        gameViewModel.updateRating(game.copy(rating = 0.0f))

                                    }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.size(48.dp))

                    }
                    Row {
                        TextButton(
                            modifier = Modifier
                                .padding(6.dp)
                                .widthIn(75.dp, 85.dp)
                                .align(Alignment.CenterVertically),


                            content = {
                                Text(
                                    text = if (selectedRating == 0.0f) stringResource(R.string.rate_it) else stringResource(
                                        R.string.to_unrate
                                    ),
                                    fontSize = 14.sp,
                                    color = if (selectedRating == 0.0f) Color.Green else if (!active && selectedRating > 0.0f) Color.Yellow else Color.Red
                                ).toString()
                            },

                            onClick = {

                                if (!active) coroutineScope.launch {

                                    selectedRating = 0.0f

                                    gameViewModel.updateRating(game.copy(rating = 0.0f))
                                }
                            }
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                //.wrapContentSize()
                                .padding(bottom = 10.dp, top = 10.dp)
                                .rotate(rotation.value),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center//Start,

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
                                        contentColor = ListItemDefaults.contentColor,
                                    ),
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .clip(CircleShape)
                                        .padding(
                                            bottom = 10.dp,
                                            top = 10.dp,
                                        )

                                ) {

                                    Text(
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .wrapContentSize(unbounded = true),
                                        text = stringResource(id = resourceId),
                                        fontSize = 12.sp,
                                        color = colorin,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        Log.e(Rate, game.rating.toString())
    }
}
