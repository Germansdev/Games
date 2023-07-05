package com.example.games.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.games.R
import com.example.games.model.GameDetails
import com.example.games.ui.AppViewModelProvider




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    //navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    gameDetails: GameDetails,
    //gameDetailsUiState: GameDetailsUiState,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),

    ) {
    /** val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
   val gameDetailsUiState =viewModel.uiState.collectAsState()*/

    Column() {
        ElevatedCard(
            modifier = Modifier//modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = navigateBack
        ) {

            Column {

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

                Column(modifier = Modifier.padding(8.dp)) {

                    Text(
                        text = gameDetails.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Text(
                        text = gameDetails.short_description,
                        style = MaterialTheme.typography.bodyLarge,
                       // maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(end = 4.dp, top = 2.dp, bottom = 4.dp)
                    )
                    Text(
                        text = gameDetails.platform,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = gameDetails.developer,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = gameDetails.freetogame_profile_url,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = gameDetails.genre,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = gameDetails.publisher,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = gameDetails.release_date,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = gameDetails.game_url,
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