package com.example.games.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.GameViewModel
import com.example.games.ui.NotPlayedViewModel

import kotlinx.coroutines.launch

private const val Tag: String = "favorite"
private const val TAG: String = "Not Played"
//private const val MY: String = "detailsScreenTo"
@Composable
fun NotPlayedScreen(
    viewModel: NotPlayedViewModel= viewModel(factory = AppViewModelProvider.Factory),
    //gameUiState: GameUiState,
    onClick: (Int) -> Unit,
    modifier: Modifier,
){
    //val homeUiState by viewModel.homeUiState.collectAsState()
    val notPlayedUiState = viewModel.notPlayedUiState.collectAsState()




    NotPlayedScreenContent(
        notPlayedL = notPlayedUiState.value.notPlayedL as List<Game>,
        modifier = Modifier,
        onClick = {onClick(it.id)},//onClick///{onClick(Game().id)}//(Int) -> Unit,
    )
}

@Composable
fun NotPlayedScreenContent(
    notPlayedL: List<Game>,
    modifier: Modifier = Modifier,
    onClick: (Game) -> Unit,
    //onClick: (Int) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),

    ) {
        Row(modifier = Modifier.padding(8.dp)
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background),
        ) {
            Text(
                text = "YOU NOT PLAYED THESE GAMES YET:",
                color = androidx.compose.material3.MaterialTheme.colorScheme.inverseSurface,
                style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(top = 8.dp)
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
                modifier = modifier.fillMaxSize(),
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
    Log.d(TAG, notPlayedL.size.toString() )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameCardNotPlayed(
    game: Game,
    gameViewModel: GameViewModel = viewModel(factory=AppViewModelProvider.Factory),
    onClick: (Game) -> Unit,
    modifier: Modifier
) {
    /**
    var favorite by remember { mutableStateOf(false) }
    favorite = gameViewModel.isFavorite(gameId = game.id)

    var play by remember { mutableStateOf(false) }
    play = gameViewModel.isPlay(gameId = game.id)

    var share by remember { mutableStateOf(false) }
    share = gameViewModel.isShare(gameId = game.id)

    var rating by remember { mutableStateOf(false) }
    rating = gameViewModel.isRate(gameId = game.id.toString())
    var selectedRating by remember { mutableStateOf(game.rating) }
*/
    val coroutineScope = rememberCoroutineScope()


    ElevatedCard(
        modifier = Modifier//modifier
            .clickable { onClick(game)  }
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

                Row(modifier = Modifier.fillMaxWidth()

                ) {

                    FavoriteButton(

                        favorite =
                        when(game.isFavorite){
                            true -> true
                            false -> false
                        },
                        onFavoriteClick = {
                            Log.e(Tag ,game.title)
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
        onClick =  onFavoriteClick
    ) {
        Icon(
            imageVector = if (favorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
            tint = if (favorite) Color.Red else Color.LightGray,
            contentDescription = null
        )
    }
}
    /**
    Column() {


        var play by remember { mutableStateOf(false) }
        val coroutineScope = rememberCoroutineScope()


            val context = LocalContext.current
            PlayButton(
                //
                play = when (game.isPlayed) {
                    true -> true
                    false -> false
                },
                onPlayClick = {  gameViewModel.selectPlayed(gameId = game.id)

                    gameViewModel.isPlay(gameId = game.id)

                    coroutineScope.launch {
                        /** if*/
                        (gameViewModel.isPlayedGame(game.copy(isPlayed = true)))

                        playGame(context, game = game)

                    }
                    play = !play},
            )
        }
}
*/