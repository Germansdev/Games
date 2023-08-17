@file:OptIn(ExperimentalLayoutApi::class)

package com.example.games.ui


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.games.GameNavHost
import com.example.games.R
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.*
import com.example.games.search.navigateToSearch
import com.example.games.ui.theme.GameIcons


@ExperimentalLayoutApi
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.R)
//@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameApp(
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass,
    appState: GameState = rememberGameAppState(
        windowSizeClass = windowSizeClass )
) {
    val navController = rememberNavController()

    var showSettingsDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        backgroundColor = Color.Transparent,
        contentColor = colorScheme.onBackground,

        bottomBar = {
            if (appState.shouldShowBottomBar) {

                BottomBar(
                    navController = navController,
                    destinations = appState.bottomBarScreens,
                    onNavigateToDestination = appState::navigateToBottomBarScreen,
                    currentDestination = appState.currentDestination
                )
            }
        },
    ) { padding ->

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = colorScheme.background,
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
                                containerColor = colorScheme.surface
                            ),
                            onActionClick = { showSettingsDialog = true },
                            onNavigationClick = { appState.navController.navigateToSearch() },

                        )

                    }

                    GameNavHost(appState, modifier = Modifier, startDestination = Pantalla1.route)
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    //with CenterAlignedTopAppBar:
    @StringRes titleRes: Int,
    navigationIcon: ImageVector,
    navigationIconContentDescription: String?,
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
    ) {
    //With CenterAlignedTopAppBar:
    CenterAlignedTopAppBar(
        title = { androidx.compose.material3.Text(text = stringResource(id = titleRes)) },
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
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    @StringRes titleRes: Int,
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = { androidx.compose.material3.Text(text = stringResource(id = titleRes)) },
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

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}

@Composable
fun BottomBar(
    navController: NavHostController,
    destinations: List<BottomBarScreen>,
    onNavigateToDestination: (BottomBarScreen) -> Unit,
    currentDestination: NavDestination?,

    modifier: Modifier = Modifier,
) {
    modifier
        .background(color = colorScheme.background)

    androidx.compose.material3.BottomAppBar() {

        BottomNavigation(
            backgroundColor = colorScheme.surface,
            contentColor = colorScheme.onSurface,
        ) {


            destinations.forEach { screen ->
             //    val navBackStackEntry by navController.currentBackStackEntryAsState()
            //     val currentDestination = navBackStackEntry?.destination

                BottomNavigationItem(

                    icon = {
                       BadgedBox(
                           badge ={
                               if (screen.badgeCount != null){
                                   Badge {
                                      Text(text = screen.badgeCount.toString())
                                   }
                               }else if (screen.hasNews){
                                   Badge()
                               }
                           }
                       ) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            tint = //if (select) Color.Red else Color.LightGray,
                            colorScheme.onSurface,
                            modifier = Modifier.clip(shapes.medium)
                        )
                       }
                    },
                    label = {
                        Text(screen.title)
                        colorScheme.inverseSurface
                    },
                    alwaysShowLabel = true,
                    unselectedContentColor = Color.Gray,


                    //selectedContentColor = colorScheme.primary,
                    selected = currentDestination.isTopLevelDestinationInHierarchy(screen),
                   // currentDestination?.hierarchy?.any { it.route == screen.route } == true,

                    onClick = {
                        onNavigateToDestination(screen) //08 08
                /**                navController.navigate(screen.route)
                                {


                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        // 08 08
                        setOfNotNull(
                        Pantalla1
                        ) //end 08 08

                                   // navBackStackEntry?.destination?.route
                        popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                        }*/
                    }
                )
            }
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: BottomBarScreen) =
    this?.hierarchy?.any {
        it.route?.contains(destination.title, true) ?: false
    } ?: false


@Composable
fun rememberGameAppState(
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass,
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
    val windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass
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
            Pantalla1 -> navController.navigate(bottomBarScreen.route)//navigateToBottomBarScreen(Pantalla1)
            Pantalla2 -> navController.navigate(bottomBarScreen.route)//navigateToBottomBarScreen(Pantalla2)
            Pantalla3 -> navController.navigate(bottomBarScreen.route)//navigateToBottomBarScreen(Pantalla3)
            Pantalla4 -> navController.navigate(bottomBarScreen.route)//navigateToBottomBarScreen(Pantalla4)
            Pantalla5 -> navController.navigate(bottomBarScreen.route)//navigateToBottomBarScreen(Pantalla5)
        }
        //   }
    }
}


