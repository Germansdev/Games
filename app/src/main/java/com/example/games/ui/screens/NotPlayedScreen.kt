package com.example.games.ui.screens

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
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material.icons.rounded.Games
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.games.R
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.GameViewModel
import com.example.games.ui.NotPlayedViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private const val Tag: String = "favorite"
private const val TAG: String = "Not Played"
private const val Tage: String = "categories"


@Composable
fun BadgeCount(
    //  viewModel: NotPlayedViewModel = viewModel(factory = AppViewModelProvider.Factory),

) {
    val viewModel: NotPlayedViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val uiState = viewModel.notPlayedUiState.collectAsState()
    val badgeCount = uiState.value.notPlayedL.size

    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        // Replace with your own badge UI
        Text(text = "Badge Count: $badgeCount")
    }
}

@Composable
fun NotPlayedScreen(
    viewModel: NotPlayedViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClick: (Int) -> Unit,
    modifier: Modifier,
    viewModels: GameViewModel = viewModel(factory = AppViewModelProvider.Factory),


    ) {
    // val gameUiState = viewModels.gameUiState
    // val homeUiState = viewModels.homeUiState.collectAsStateWithLifecycle()
    //val games = viewModels.homeUiState.collectAsState() as List<Game>

    val notPlayedUiState = viewModel.notPlayedUiState.collectAsStateWithLifecycle()

    //  val notPlayedGames by notPlayedViewModel.notPlayedUiState.collectAsState(emptyList())

    NotPlayedScreenContent(
        notPlayedL = notPlayedUiState.value.notPlayedL as List<Game>,
        modifier = Modifier,
        onClick = { onClick(it.id) },//onClick///{onClick(Game().id)}//(Int) -> Unit,
        games = listOf(Game())//listOf(Game(categories.toString()))//categories.value.itemList//categories.value.itemList,
    )
}

@Composable
fun NotPlayedScreenContent(
    notPlayedL: List<Game>,
    modifier: Modifier = Modifier,
    onClick: (Game) -> Unit,
    //onClick: (Int) -> Unit
    games: List<Game>
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
                            Color.Yellow,
                            Color.White
                        )
                    }
                )
            )
            .fillMaxSize()
        // .padding(8.dp),

    ) {
        // Text(text = "Test Layaout")
        Spacer(modifier = Modifier.size(16.dp))
        myLazzyRow(
            modifier = modifier.fillMaxHeight(),
            notPlayedL = notPlayedL
        )
//("Tage, ${games.size}")
        d(TAG, notPlayedL.size.toString())
        d(TAG, notPlayedL.size.toString())

        Row(
            modifier = Modifier
                .padding(top = 8.dp)
                //.background(colorScheme.background)
                .align(CenterHorizontally)
            // .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background),
        ) {

            Text(
                text = "YOU  HAVE NOT PLAYED THESE GAMES YET:",
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                // androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
                modifier = Modifier

                    .background(if (isSystemInDarkTheme()) Color.Black else Color.Yellow)

                /**     .background(
                brush = Brush.verticalGradient(
                if(  isSystemInDarkTheme()) {
                listOf(
                Color.Black,
                Color.Blue
                )
                }else{
                listOf(
                Color.Yellow,
                Color.White
                )
                }
                )
                )*/
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

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
                        modifier = modifier.fillMaxSize()
                    )
                }
            }
        }
    }
    d(TAG, notPlayedL.size.toString())

}

@Composable
fun myLazzyRow(
    modifier: Modifier,
    notPlayedL: List<Game>
) {
   // val notPlayedViewModel: NotPlayedViewModel = viewModel(factory = AppViewModelProvider.Factory)
   // val notPlayedViewModel : NotPlayedViewModel = viewModel()
    //val myCategory = notPlayedViewModel.myCategory.collectAsStateWithLifecycle(initialValue = notPlayedL )

   // val notPlayedL = MutableStateFlow<List<Game>>(emptyList())
   // val myCategory = notPlayedL.map{ categoryListR -> categoryListR.map { it.genre}.distinct() }


    //original works ok, but get all genre of al games
    val mycategory: Set<String> = setOf()
   mycategory.map {
        notPlayedL.groupBy { game ->
            game.genre
                .forEach { setOf(game.genre) }
        }
    }
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxSize()

    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize(),

            // .height(100.dp),
            //.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            //verticalAlignment = Arrangement.Center,

        ) {
            items(
                 notPlayedL,

             //     (myCategory.value),
             key = { game -> game.id }
            //key = {game-> game}
            ) { game ->
                MyCard(
                    onClick = { game },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(CircleShape),
                    game = game.copy(isPlayed = false)
                )
            }
        }
    }
}

@Composable
fun MyCard(
    gameViewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClick: (Game) -> Unit,
    modifier: Modifier = Modifier,
    game: Game = Game()

) {

    OutlinedCard(
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxSize()
            .fillMaxWidth()
            .height(60.dp)
            //.height(40.dp)
            .padding(8.dp)
            .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
            .clickable { onClick(game) }

    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {

            Row(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)

            ) {
                Text(
                    modifier = modifier
                        .align(CenterVertically),
                    text = game.genre
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCardNotPlayed(
    game: Game,
    gameViewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onClick: (Game) -> Unit,
    modifier: Modifier,

    ) {

    val coroutineScope = rememberCoroutineScope()

    ElevatedCard(
        modifier = Modifier//modifier
            //  .clickable { onClick(game) }
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(8.dp),

        ) {
        // Log.e(MY ,game.title)
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
                            e(Tag, game.title)
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
                        val summary: String = "Play this game, is incredible"
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
                                    link,
                                    game = game
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
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
    //Log.e(MY ,game.title)
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
    context: Context, subject: String, summary: String, link: String, game: Game,
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
            imageVector = if (share) Icons.Rounded.Share else Icons.Outlined.Share,
            contentDescription = null,
            //tint = if (share) Color.Red else Color.LightGray,
            tint = Color.LightGray,
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
            imageVector = if (play) Icons.Rounded.Games else Icons.Rounded.Games,
            //tint = if (play) Color.Red else Color.LightGray,
            contentDescription = null,
            tint = Color.LightGray
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