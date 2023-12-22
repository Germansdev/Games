package com.example.games.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.games.DetailsViewModel
import com.example.games.GameDetailsUiState
import com.example.games.R
import com.example.games.appDestinations.NavigationDestination
import com.example.games.model.Game
import com.example.games.model.GameEntity
import com.example.games.ui.AppViewModelProvider
import com.example.games.ui.CustomTopBar
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    // 19/12: gameDetails: Game,
    gameDetails: GameEntity,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    shouldShowGradientBackground: Boolean,
    //scaffoldState: ScaffoldState = rememberScaffoldState()

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


        /**    Scaffold(
                modifier = Modifier
                    //.fillMaxSize()
                    .padding(bottom = 16.dp)
                    .scrollable(
                        ScrollableState { 1F },
                        //ScrollableDefaults,
                        //  overscrollEffect = Orientation.Horizontal,
                        orientation = Orientation.Horizontal,
                        // overscrollEffect = null,
                        //enabled = true,
                    )
                ,
                containerColor= Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,

            ) {*/
                Column(modifier = Modifier
                    .padding(8.dp)
                    //.heightIn(min=650.dp, max= 750.dp)
                    //.fillMaxHeight()
                    .scrollable(
                        ScrollableState { 1F },

                        //ScrollableDefaults,
                      //  overscrollEffect = Orientation.Horizontal,
                        orientation = Orientation.Horizontal,
                       // overscrollEffect = null,
                        //enabled = true,
                    )
                ) {


                ItemDetailsBody(
                    gameDetailsUiState = uiState.value,
                    onBack = onClick,
                    modifier = modifier
                        .fillMaxHeight()
                        //.fillMaxSize()
                        .scrollable(
                            ScrollableState { 1F },
                            //ScrollableDefaults,
                            //  overscrollEffect = Orientation.Horizontal,
                            orientation = Orientation.Horizontal,
                            // overscrollEffect = null,
                            enabled = true,
                        )
                   // .safeContentPadding()
                )
                //Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
                Log.d(TAG, gameDetails.title)
            }
      //  }
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


    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
    Column(

        modifier = Modifier

            .fillMaxHeight()
           // .padding(bottom = 8.dp)
            .fillMaxHeight()
    ) {
        Column {

            ElevatedCard(

                modifier = modifier//modifier
                    //     .clickable { onBack() }
                    .padding(bottom = 100.dp)
                    .fillMaxWidth()
                    //.wrapContentSize()
                   // .fillMaxHeight()
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
                            Divider(
                                thickness = 2.dp,
                                modifier = Modifier.padding(top = 8.dp),
                                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray
                            )
                            Text(
                                color = MaterialTheme.colorScheme.inverseSurface,
                                text = gameDetails.short_description,
                                style = MaterialTheme.typography.bodySmall,
                                //fontSize = 12.sp,
                                // maxLines = 2,
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
                            Row(modifier = Modifier
                                .padding(end = 16.dp)
                                .align(Alignment.End)
                            ) {
                                GameOutlinedButton(
                                    onClick = {onBack()},
                                    enabled = true,
                                    text = { Text(text = "Back")},
                                    leadingIcon = {
                                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
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
    // TODO: File bug
    // OutlinedButton border color doesn't respect disabled state by default
    const val DisabledOutlinedButtonBorderAlpha = 0.12f

    // TODO: File bug
    // OutlinedButton default border width isn't exposed via ButtonDefaults
    val OutlinedButtonBorderWidth = 1.dp
}

