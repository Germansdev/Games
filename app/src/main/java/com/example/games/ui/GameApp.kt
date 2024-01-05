@file:OptIn(ExperimentalLayoutApi::class)

package com.example.games.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log.e
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.Companion.Compact
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.games.GameNavHost
import com.example.games.GamesNavigationBar
import com.example.games.GamesNavigationBarItem
import com.example.games.GamesNavigationDefaults
import com.example.games.GamesNavigationDefaults.navigationContentColor
import com.example.games.GamesNavigationRail
import com.example.games.GamesNavigationRailItem
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.*
import com.example.games.search.navigateToSearch
import com.example.games.ui.badges.FavoritesBadgeViewModel
import com.example.games.ui.badges.NotPlayedBadgeViewModel
import com.example.games.ui.badges.PlayedBadgeViewModel
import com.example.games.ui.badges.SharedBadgeViewModel
import com.example.games.ui.badges.StatsBadgeViewModel
import com.example.games.ui.theme.GamesBackground
import com.example.games.ui.theme.GamesGradientBackground
import com.example.games.ui.theme.GamesIcons
import com.example.games.ui.theme.Icon
import com.example.games.ui.theme.Icon.*
import com.example.games.ui.theme.LocalGradientColors


@ExperimentalLayoutApi
@OptIn(
    ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@RequiresApi(Build.VERSION_CODES.R)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "NewApi")
@Composable
fun GamesApp(
    windowSizeClass: WindowSizeClass,
    appState: GamesState = rememberGamesAppState(
        windowSizeClass = windowSizeClass
    ),
) {

    val shouldShowGradientBackground =
        appState.currentTopLevelDestination == Pantalla1

    var showSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    GamesBackground {
        GamesGradientBackground(
            gradientColors = if (shouldShowGradientBackground) {
                LocalGradientColors.current
            } else {
                LocalGradientColors.current
                // error:
                //   GradientColors()
            },
        ) {

            if (showSettingsDialog) {
                SettingsDialog(
                    onDismiss = { showSettingsDialog = false },
                )
            }

            Scaffold(
                modifier = Modifier
                    .semantics {
                        testTagsAsResourceId = true
                    },
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                containerColor = Transparent,
                contentColor = colorScheme.onBackground,

                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        BottomBar(
                            destinations = appState.bottomBarScreens,
                            onNavigateToDestination = appState::navigateToBottomBarScreen,
                            currentDestination = appState.currentDestination,
                            notPlayedBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            favoritesBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            playedBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            statsBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            sharedBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            modifier = Modifier,
                        )
                    }
                },

                ) { padding ->

                Row(
                    Modifier
                        .fillMaxSize()
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal,
                            ),
                        ),

                    ) {

                    if (appState.shouldShowNavRail) {
                        GameNavRail(
                            destinations = appState.bottomBarScreens,
                            onNavigateToDestination = appState::navigateToBottomBarScreen,
                            currentDestination = appState.currentDestination,
                            notPlayedBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            favoritesBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            playedBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            statsBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            sharedBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory),
                            modifier = Modifier
                                .safeDrawingPadding()
                        )
                    }

                    Column(Modifier.fillMaxSize()) {

                        val destination = appState.currentTopLevelDestination
                        if (destination != null) {
                            CustomTopBar(
                                titleRes = destination.titleTextId,
                                navigationIcon = GamesIcons.Search,
                                navigationIconContentDescription = null,
                                actionIcon = GamesIcons.Settings,
                                actionIconContentDescription = null,
                                colors = TopAppBarDefaults
                                    .centerAlignedTopAppBarColors(
                                        titleContentColor = GamesNavigationDefaults.navigationSelectedItemColor(),
                                        containerColor = Transparent,
                                    ),
                                onActionClick = { showSettingsDialog = true },
                                onNavigationClick = { appState.navController.navigateToSearch() },
                            )
                        }

                        GameNavHost(
                            appState,
                            modifier = Modifier,
                            startDestination = Pantalla1.route,
                        )
                    }
                }
            }
        }
    }
}

/**
 *This used in GameApp and AppNavHost see what happend and reduce code
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    //with CenterAlignedTopAppBar:
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    genre: String? = "",
    navigationIcon: ImageVector?,
    navigationIconContentDescription: String?,
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},

    ) {
    //With CenterAlignedTopAppBar:
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = titleRes),
                fontSize = 16.sp,
            )
            if (genre != null) {
                if (genre.isNotEmpty()) {
                    Text(
                        text = "Genre:/bar1 $genre",
                        style = MaterialTheme.typography.titleLarge,
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    )
                }
            }
        },
        navigationIcon = {

            androidx.compose.material3.IconButton(onClick = onNavigationClick) {
                if (navigationIcon != null) {
                    androidx.compose.material3.Icon(
                        imageVector = navigationIcon,
                        contentDescription = navigationIconContentDescription,
                        tint = colorScheme.onSurface,
                    )
                }
            }
        },
        actions = {
            androidx.compose.material3.IconButton(onClick = onActionClick) {
                androidx.compose.material3.Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = colorScheme.onSurface,
                )
            }
        },
        colors = colors,
        modifier = modifier,
    )
}

/**
 * Top app bar with action, displayed on the right
 * this called in GameListCategoryScreen and DetailsScreen
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    //  modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    genre: String = "",
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onActionClick: () -> Unit = {},
) {

    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(id = titleRes))
            if (genre.isNotEmpty()) {
                Text(
                    text = "Genre: $genre",
                    style = MaterialTheme.typography.titleLarge,
                    color = GamesNavigationDefaults.navigationSelectedItemColor(),
                )
            }
        },
        actions = {
            androidx.compose.material3.IconButton(onClick = onActionClick) {
                androidx.compose.material3.Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = colorScheme.onSurface,
                )
            }
        },
        colors = colors
    )
}

@Composable
fun Badge(
    badgeCount: Int,
) {

    BadgedBox(
        modifier = Modifier
            .offset(x = (12).dp, y = (-4).dp)
            .padding(2.dp),

        badge = {

            if (badgeCount != 0) {
                Text(
                    text = badgeCount.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center,
                    softWrap = true,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(12.dp)
                        .widthIn(17.dp, 20.dp)
                        .height(16.dp)
                        .clip(CircleShape)
                        .background(if (badgeCount != 0) Color.Red else Transparent)
                )
            }
        },
        content = {}
    )
}

//adapt the Bottom bar to include the functionality of badges:
@Composable
fun BottomBar(
    destinations: List<BottomBarScreen>,
    onNavigateToDestination: (BottomBarScreen) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
    notPlayedBadgeViewModel: NotPlayedBadgeViewModel,
    favoritesBadgeViewModel: FavoritesBadgeViewModel,
    playedBadgeViewModel: PlayedBadgeViewModel,
    statsBadgeViewModel: StatsBadgeViewModel,
    sharedBadgeViewModel: SharedBadgeViewModel
) {

    val notPlayedB = notPlayedBadgeViewModel.uiState.collectAsState()
    val favoriteB = favoritesBadgeViewModel.uiState.collectAsState()
    val playedB = playedBadgeViewModel.uiState.collectAsState()
    val statsB = statsBadgeViewModel.uiState.collectAsState()
    val sharedB = sharedBadgeViewModel.uiState.collectAsState()

    val badgeCountNotPlayed = notPlayedB.value
    val badgeCountFavorite = favoriteB.value
    val badgeCountPlayed = playedB.value
    val badgeCountStats = statsB.value
    val badgeCountShared = sharedB.value

    GamesNavigationBar(modifier = modifier) {

        destinations.forEach { screen ->

            val badgeCounts = when (screen) {

                Pantalla1 -> {
                    badgeCountNotPlayed.badgeCount.toString()
                }

                Pantalla2 -> {
                    badgeCountFavorite.badgeCount.toString()
                }

                Pantalla3 -> {
                    badgeCountPlayed.badgeCount.toString()
                }

                Pantalla4 -> {
                    badgeCountStats.badgeCount.toString()
                }

                else -> {
                    badgeCountShared.badgeCount.toString()
                }
            }

            GamesNavigationBarItem(
                selected = currentDestination.isTopLevelDestinationInHierarchy(screen),
                onClick = { onNavigateToDestination(screen) },
                icon = {

                    Badge(badgeCount = badgeCounts.toInt())

                    val icon: Icon =
                        if (currentDestination.isTopLevelDestinationInHierarchy(screen)) {
                            screen.selectedIcon

                        } else {
                            screen.unselectedIcon
                        }
                    when (icon) {
                        is ImageVectorIcon -> androidx.compose.material3.Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null,
                        )

                        is DrawableResourceIcon -> androidx.compose.material3.Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null,
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = screen.iconTextId),
                        fontSize = 12.sp,
                        softWrap = false,
                        color = if (currentDestination.isTopLevelDestinationInHierarchy(screen)
                        ) {
                            GamesNavigationDefaults.navigationSelectedItemColor()
                        } else {
                            navigationContentColor()
                        }
                    )
                },
                modifier = Modifier.sizeIn(8.dp, 10.dp),
            )
            e(TAG, notPlayedB.toString())
        }
    }
    e(TAG, favoriteB.value.toString())
}

fun NavDestination?.isTopLevelDestinationInHierarchy(destination: BottomBarScreen) =
    this?.hierarchy?.any {
        it.route?.contains((destination.route), true) ?: false
    } ?: false

@Composable
fun GameNavRail(
    destinations: List<BottomBarScreen>,
    onNavigateToDestination: (BottomBarScreen) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
    notPlayedBadgeViewModel: NotPlayedBadgeViewModel,
    favoritesBadgeViewModel: FavoritesBadgeViewModel,
    playedBadgeViewModel: PlayedBadgeViewModel,
    statsBadgeViewModel: StatsBadgeViewModel,
    sharedBadgeViewModel: SharedBadgeViewModel
) {

    val notPlayedB = notPlayedBadgeViewModel.uiState.collectAsState()
    val favoriteB = favoritesBadgeViewModel.uiState.collectAsState()
    val playedB = playedBadgeViewModel.uiState.collectAsState()
    val statsB = statsBadgeViewModel.uiState.collectAsState()
    val sharedB = sharedBadgeViewModel.uiState.collectAsState()

    val badgeCountNotPlayed = notPlayedB.value
    val badgeCountFavorite = favoriteB.value
    val badgeCountPlayed = playedB.value
    val badgeCountStats = statsB.value
    val badgeCountShared = sharedB.value

    GamesNavigationRail(
        modifier = modifier
    ) {

        destinations.forEach { screen ->

            val badgeCounts = when (screen) {

                Pantalla1 -> {
                    badgeCountNotPlayed.badgeCount.toString()
                }

                Pantalla2 -> {
                    badgeCountFavorite.badgeCount.toString()
                }

                Pantalla3 -> {
                    badgeCountPlayed.badgeCount.toString()
                }

                Pantalla4 -> {
                    badgeCountStats.badgeCount.toString()
                }

                else -> {
                    badgeCountShared.badgeCount.toString()
                }
            }

            GamesNavigationRailItem(

                icon = {

                    Badge(badgeCount = badgeCounts.toInt())


                    val icon: Icon = if (currentDestination.isTopLevelDestinationInHierarchy(screen)
                    ) screen.selectedIcon else screen.unselectedIcon
                    when (icon) {
                        is ImageVectorIcon -> androidx.compose.material3.Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null,
                        )

                        is DrawableResourceIcon -> androidx.compose.material3.Icon(
                            painter = painterResource(id = icon.id),
                            contentDescription = null,
                        )
                    }
                },

                label = {
                    Text(
                        text = stringResource(id = screen.iconTextId),
                        color = if (currentDestination.isTopLevelDestinationInHierarchy(screen)
                        ) {
                            GamesNavigationDefaults.navigationSelectedItemColor()
                        } else {
                            navigationContentColor()
                        }

                    )
                },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { onNavigateToDestination(screen) }
            )
            e(TAG, notPlayedB.toString())
        }
        e(TAG, favoriteB.value.toString())
    }
}

@Composable
fun rememberGamesAppState(
    windowSizeClass: WindowSizeClass, //nia App
    //networkStatus: NetworkStatus, //implement hilt

    //   coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),


    ): GamesState {
    return remember(
        navController,
        //   coroutineScope,
        windowSizeClass,
    ) {
        GamesState(
            navController,
            //   coroutineScope,
            windowSizeClass,
        )
    }
}

class GamesState(
    val navController: NavHostController,
    //  val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination
    val currentTopLevelDestination: BottomBarScreen?
        @Composable get() = when (currentDestination?.route) {
            Pantalla1.route -> Pantalla1
            Pantalla2.route -> Pantalla2
            Pantalla3.route -> Pantalla3
            Pantalla4.route -> Pantalla4
            Pantalla5.route -> Pantalla5
            else -> null
        }
    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == Compact
    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar


    val bottomBarScreens: List<BottomBarScreen> = listOf(
        Pantalla1, Pantalla2, Pantalla3, Pantalla4, Pantalla5
    )

    fun navigateToBottomBarScreen(bottomBarScreen: BottomBarScreen) {

        /**val bottomBarScreens/**topLevelNavOptions*/ =*/
        navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (bottomBarScreen) {
            Pantalla1 -> navController.navigate(bottomBarScreen.route)
            Pantalla2 -> navController.navigate(bottomBarScreen.route)
            Pantalla3 -> navController.navigate(bottomBarScreen.route)
            Pantalla4 -> navController.navigate(bottomBarScreen.route)
            Pantalla5 -> navController.navigate(bottomBarScreen.route)
        }
    }
}
