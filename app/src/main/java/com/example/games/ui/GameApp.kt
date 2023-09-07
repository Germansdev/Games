@file:OptIn(ExperimentalLayoutApi::class)

package com.example.games.ui


import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
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
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.*
import com.example.games.search.navigateToSearch
import com.example.games.ui.badges.FavoritesBadgeViewModel
import com.example.games.ui.badges.NotPlayedBadgeViewModel
import com.example.games.ui.badges.PlayedBadgeViewModel
import com.example.games.ui.badges.SharedBadgeViewModel
import com.example.games.ui.badges.StatsBadgeViewModel
import com.example.games.ui.theme.GameIcons



@ExperimentalLayoutApi
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameApp(
    windowSizeClass: WindowSizeClass,
    appState: GameState = rememberGameAppState(
        windowSizeClass = windowSizeClass
    )
) {

    var showSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    //with NavigationBar:
    /**   var bottomBarState by rememberSaveable{
    mutableStateOf(0)
    }*/

    Scaffold(

        backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        contentColor = if (isSystemInDarkTheme()) Color.Black else Color.White,

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
                    sharedBadgeViewModel = viewModel(factory = AppViewModelProvider.Factory)
                )
            }
        },
    ) { padding ->

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = if (isSystemInDarkTheme()) Color.Black else Color.White,

            ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),

                ) {
                Column(Modifier.fillMaxSize()) {
                    val destination = appState.currentTopLevelDestination
                    if (destination != null) {
                        CustomTopBar(
                            titleRes = destination.titleTextId,//R.string.app_name,//destination.title.toInt(),//R.string.app_name, }
                            navigationIcon = GameIcons.Search,
                            navigationIconContentDescription = null,
                            actionIcon = GameIcons.Settings,
                            actionIconContentDescription = null,
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,

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

/**
  *This used in GameApp and AppNavHost see what happend and reduce code
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    //with CenterAlignedTopAppBar:
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    genre: String = "",
    navigationIcon: ImageVector,
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
            androidx.compose.material3.Text(text = stringResource(id = titleRes))
            if (genre.isNotEmpty()) {
                Text(
                    text = "Genre: $genre",
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                )

            }
        },
        navigationIcon = {
            androidx.compose.material3.IconButton(onClick = onNavigationClick) {
                androidx.compose.material3.Icon(
                    imageVector = navigationIcon,
                    contentDescription = navigationIconContentDescription,
                    tint = colorScheme.onSurface,
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
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    genre: String = "",
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            androidx.compose.material3.Text(text = stringResource(id = titleRes))
            if (genre.isNotEmpty()) {
                Text(
                    text = " Genre: $genre",
                    style = MaterialTheme.typography.titleLarge,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
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
        colors = colors,
        modifier = modifier,

        )
}
//END WITH SPECIAL TOP BAR

//BOTTOM NAVIGATION:
/**
@Composable
fun currentRoute(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}*/

@Composable
fun Badge(
    badgeCount: Int,
   // modifier: Modifier =Modifier,
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
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    softWrap = true,
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .wrapContentSize()
                        .padding(12.dp)
                        .width(17.dp)
                        .height(16.dp)
                        .clip(CircleShape)
                        .background(if (badgeCount != 0) Color.Red else Color.Transparent)
                )
            }
        },
        content = {}
    )
    }


//adapt the Bottom bar to include the functionality of badges:
@Composable
fun BottomBar(
 //   navController: NavHostController,
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


    Divider(
        modifier = Modifier.fillMaxWidth(),
        color = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
        startIndent = 4.dp
    )

    androidx.compose.material3.BottomAppBar(
        containerColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        contentColor = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
        modifier = Modifier

            .background(colorScheme.background)
            //.padding(10.dp)
            .clip(RoundedCornerShape(8.dp)),

        ) {
        /**
        BottomNavigation(
        backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        contentColor = if (isSystemInDarkTheme()) Color.White else Color.DarkGray,
        modifier = Modifier
        .background(colorScheme.background)
        //.padding(10.dp)
        .clip(RoundedCornerShape(8.dp)),
        ) {*/
        //with NavigationBar:

    /**    destinations.forEachIndexed { index, bottomBarScreen ->
           // val badgeCount = bottomBarScreen.badgeCount
            //val isBadgeVisible = badgeCount != 0


        }*/


        destinations.forEach { screen ->
       /**     val badgeCount = screen.badgeCount
            val isBadgeVisible = badgeCount != 0 */

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

            BottomNavigationItem(

                icon = {

                    Badge(badgeCount = badgeCounts.toInt() )

                        Icon(
                            imageVector = if (currentDestination.isTopLevelDestinationInHierarchy(
                                    screen
                                )
                            ) screen.selectedIcon else screen.unselectedIcon,
                            contentDescription = screen.title,
                            modifier = Modifier
                        )
                },
                label = { Text(screen.title, fontWeight = FontWeight.Bold) },
                alwaysShowLabel = true,
                unselectedContentColor = Color.Gray,
                selectedContentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { onNavigateToDestination(screen) }
            )
            Log.e(TAG, notPlayedB.toString())
        }
    }
    e(TAG, favoriteB.value.toString())
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: BottomBarScreen) =
    this?.hierarchy?.any {
        it.route?.contains(destination.title, true) ?: false
    } ?: false

@Composable
fun rememberGameAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController(),
): GameState {
    return remember(
        navController,
        windowSizeClass
    ) {
        GameState(
            navController,
            windowSizeClass
        )
    }
}

class GameState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val bottomBarScreens: List<BottomBarScreen> = listOf(
        Pantalla1, Pantalla2, Pantalla3, Pantalla4, Pantalla5
    ) //ojo!!!
    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val currentTopLevelDestination: BottomBarScreen?
        @Composable get() = when (currentDestination?.route) {
            Pantalla1.route -> Pantalla1
            Pantalla2.route -> Pantalla2
            Pantalla3.route -> Pantalla3
            Pantalla4.route -> Pantalla4
            Pantalla5.route -> Pantalla5
            else -> null
        }

    fun navigateToBottomBarScreen(bottomBarScreen: BottomBarScreen) {
        //   trace("Navigation: ${topLevelDestination.name}") {
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