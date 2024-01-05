package com.example.games.ui.screens

import android.content.res.Configuration
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import com.example.games.ui.theme.GamesBackground
import com.example.games.ui.theme.GamesGradientBackground
import com.example.games.ui.theme.LocalGradientColors

private const val TAG: String = "DetailScreen"

object ItemDetailsDestination : NavigationDestination {
    override val route = "item_details"
    override val titleRes = R.string.details
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@RequiresApi(34)
@Composable
fun DetailsScreen(
    gameDetails: Game,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    shouldShowGradientBackground: Boolean,

    ) {

    val uiState = viewModel.uiState.collectAsState()

    GamesBackground {
        GamesGradientBackground(

            gradientColors = if (shouldShowGradientBackground) {
                LocalGradientColors.current

                // GradientColors()
            } else {
                LocalGradientColors.current
                //GradientColors()
            },
        ) {

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .scrollable(
                        ScrollableState { 1F },
                        orientation = Orientation.Vertical,
                    )

            ) {

                ItemDetailsBody(
                    gameDetailsUiState = uiState.value,
                    onBack = onClick,
                    modifier = modifier
                        .fillMaxHeight()
                        .scrollable(
                            ScrollableState { 1F },
                            orientation = Orientation.Horizontal,
                            enabled = true,
                        )
                )
                Log.d(TAG, gameDetails.title)
            }
        }
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()

    ) {

        Column(

            modifier = Modifier
                .fillMaxHeight()
               //¿ .fillMaxHeight()

        ) {

            Column(
                modifier = Modifier

            ) {
                val configuration = LocalConfiguration.current
                val stateVertical = rememberScrollState(0)

                if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

                    ElevatedCard(

                        modifier = modifier

                            .verticalScroll(state = stateVertical)

                            .padding(bottom = 100.dp)
                            //.fillMaxWidth()
                            //.fillMaxSize()
                            .heightIn(500.dp, 600.dp)
                            .padding(top = 35.dp, bottom = 10.dp, start = 10.dp, end = 10.dp),
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
                            Divider(
                                thickness = 2.dp,
                                modifier = Modifier.padding(top = 8.dp),
                                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
                            )
                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.short_description,
                                style = MaterialTheme.typography.bodySmall,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(end = 4.dp, top = 2.dp)
                            )
                            Divider(
                                thickness = 2.dp,
                                modifier = Modifier.padding(top = 8.dp),
                                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
                            )
                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.developer,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.game_url,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.freetogame_profile_url,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.genre,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.platform,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.publisher,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.release_date,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .align(Alignment.End)
                            ) {
                                GameOutlinedButton(
                                    onClick = { onBack() },
                                    enabled = true,
                                    text = { Text(text = "Back") },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }
                    }
                }

                else{

                    ElevatedCard(

                        modifier = modifier

                            .verticalScroll(state = stateVertical)
                            .padding(bottom = 100.dp)
                            .fillMaxWidth()
                            .heightIn(500.dp, 600.dp)
                            .padding(top = 35.dp, bottom = 10.dp, start = 10.dp, end = 10.dp),
                        elevation = CardDefaults.cardElevation(5.dp),
                        shape = RoundedCornerShape(8.dp),

                        ) {

                        Row {
                            Column(modifier = Modifier
                                .fillMaxSize()
                                .weight(.5f)
                                .padding(top = 16.dp, start = 8.dp)
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    model = ImageRequest.Builder(context = LocalContext.current)
                                        .data(gameDetails.thumbnail)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    error = painterResource(id = R.drawable.ic_broken_image),
                                    placeholder = painterResource(id = R.drawable.loading_img)
                                )
                            }



                        Column(modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                            .weight(.5f)
                        ) {

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.title,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                            Divider(
                                thickness = 2.dp,
                                modifier = Modifier.padding(top = 8.dp),
                                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
                            )
                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.short_description,
                                style = MaterialTheme.typography.bodySmall,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(end = 4.dp, top = 2.dp)
                            )
                            Divider(
                                thickness = 2.dp,
                                modifier = Modifier.padding(top = 8.dp),
                                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
                            )
                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.developer,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.game_url,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.freetogame_profile_url,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.genre,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.platform,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.publisher,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )

                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.release_date,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.Normal,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                            Row(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .align(Alignment.End)
                            ) {
                                GameOutlinedButton(
                                    onClick = { onBack() },
                                    enabled = true,
                                    text = { Text(text = "Back") },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.ArrowBack,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }
                    }
                    }
                }
            }
        }
    }
}

@Composable
fun GameOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        border = BorderStroke(
            width = GameButtonDefaults.OutlinedButtonBorderWidth,
            color = if (enabled) {
                MaterialTheme.colorScheme.outline
            } else {
                MaterialTheme.colorScheme.onSurface.copy(
                    alpha = GameButtonDefaults.DisabledOutlinedButtonBorderAlpha,
                )
            },
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
fun GameOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    GameOutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = if (leadingIcon != null) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        },
    ) {
        GameButtonContent(
            text = text,
            leadingIcon = leadingIcon,
        )
    }
}

@Composable
private fun GameButtonContent(
    text: @Composable () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
) {
    if (leadingIcon != null) {
        Box(Modifier.sizeIn(maxHeight = ButtonDefaults.IconSize)) {
            leadingIcon()
        }
    }
    Box(
        Modifier
            .padding(
                start = if (leadingIcon != null) {
                    ButtonDefaults.IconSpacing
                } else {
                    0.dp
                },
            ),
    ) {
        text()
    }
}

/**
 * Now in Android button default values.
 */
object GameButtonDefaults {

    // OutlinedButton border color doesn't respect disabled state by default
    const val DisabledOutlinedButtonBorderAlpha = 0.12f

    // OutlinedButton default border width isn't exposed via ButtonDefaults
    val OutlinedButtonBorderWidth = 1.dp
}

