package com.example.games.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.games.DetailsViewModel
import com.example.games.GameDetailsUiState
import com.example.games.R
import com.example.games.appDestinations.NavigationDestination
import com.example.games.model.Game
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.CustomTopBar

private const val TAG: String = "DetailScreen"

object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.details
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    gameDetails: Game,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),

    ) {
    /**VERY IMPORTANT:
     * Always recover the uiState as Cold Flow of each State of everything*/

    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CustomTopBar(
                titleRes = R.string.details,
                actionIcon = Icons.Default.ArrowBack,
                actionIconContentDescription = null
            )
        }) { innerPadding ->
        ItemDetailsBody(
            gameDetailsUiState = uiState.value,
            onBack = onClick,
            modifier = modifier.padding(innerPadding)

        )
        Log.d(TAG, gameDetails.title)
    }
}

@Composable
fun ItemDetailsBody(
    gameDetailsUiState: GameDetailsUiState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {

    /**VERY IMPORTANT:
     * with this val recover the actual state of gameDetails: Game
     * (the choose game to see its details
     * not show anything without this val, navigate to DetailsScreen,
     * but not show the composition (broken image)*/
    val gameDetails = gameDetailsUiState.gameDetails


    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "this NotPlayedCard")
    }
    Column(

        modifier = Modifier
            //.fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
        // .height(500.dp)
    ) {
        Column {
            ElevatedCard(
                modifier = modifier//modifier
                    .clickable { onBack() }
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
                elevation = CardDefaults.cardElevation(5.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(gameDetails.thumbnail)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(id = R.drawable.loading_img)
                )

                Column(
                ) {

                    Column {

                        Column(modifier = Modifier.padding(8.dp)) {

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.title,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.short_description,
                                style = MaterialTheme.typography.bodyLarge,
                                // maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(end = 4.dp, top = 2.dp, bottom = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.developer,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.game_url,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.freetogame_profile_url,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.genre,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.platform,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.publisher,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.release_date,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}






