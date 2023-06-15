package com.example.games.ui


import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.games.GameNavHost
import com.example.games.R
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.*

//import com.example.games.GameNavHost

//TOP BAR COMPOSABLE TO NAVIGATE CUSTOM:
/**
enum class NavigationScreens(@StringRes val tittle: Int) {
    HomeScreen(tittle = R.string.homescreen),
    ListScreen(tittle = R.string.app_name),
    FavoriteScreen(tittle = R.string.favorites),
    PlayedScreen(tittle = R.string.played),
    SharedScreen(tittle = R.string.shared),
    RatedScreen(tittle = R.string.rated)
}*/

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameApp(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = viewModel(),

    ) {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen

    //THIS IS BOTTOM BAR TOP LEVEL DESTINATION:

    val items = listOf(
        Pantalla1,
        Pantalla2,
        Pantalla3,
        Pantalla4,
        Pantalla5,
    )

// https://developer.android.com/jetpack/compose/navigation?hl=es-419

    Scaffold(
        topBar = {
            CustomTopBar(
               // currentScreenTitle = currentScreen.tittle,
              //  canNavigateBack = false,
               // navigateUp = { navController.navigateUp() })
        },

        bottomBar = {
            BottomBar(navController = navController, items = items)
        }

    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = colorScheme.background,
        ) {

            GameNavHost(navController = navController)
        }
    }
}


@Composable
fun CustomTopBar(
//   @StringRes currentScreenTitle: Int,
 //   canNavigateBack: Boolean,
  //  navigateUp: () -> Unit,
    setting:()-> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.games),
            /**Text(stringResource(currentScreenTitle)*/
            )
        },
        contentColor = colorScheme.inverseSurface,
        backgroundColor = colorScheme.surface,
       /** navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)

                    )
                }
            }
        }*/
        actions =
        ,
        modifier = modifier,
    )
}

//BOTTOM NAVIGATION:

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}

@Composable
fun BottomBar(
    navController: NavHostController,
    items: List<BottomBarScreen>,
    modifier: Modifier = Modifier,
) {
    modifier.background(color = colorScheme.background)

    androidx.compose.material3.BottomAppBar() {

        BottomNavigation(
            backgroundColor = colorScheme.surface,
            contentColor = colorScheme.inverseSurface,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            tint = Color.Gray,
                        )
                    },
                    label = {
                        Text(screen.title)
                        colorScheme.inverseSurface
                    },
                    alwaysShowLabel = false,
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
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
                    }
                )
            }
        }
    }
}

