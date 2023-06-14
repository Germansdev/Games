package com.example.games.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.games.GameNavHost
import com.example.games.appDestinations.BottomBarScreen
import com.example.games.appDestinations.BottomBarScreen.*
import com.example.games.ui.screens.HomeScreen
import com.example.games.ui.theme.md_theme_light_surfaceTint

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

/**

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(

@StringRes currentScreenTitle: Int,
canNavigateBack: Boolean,
navigateUp: () -> Unit,
//onMenuClick: () -> Unit,
//onNavigateUpClicked: () -> Unit,
modifier: Modifier = Modifier,

) {
TopAppBar(
title = {
Text(
stringResource(id = currentScreenTitle),
//text = currentScreen.title,
// text = "Games",
fontWeight = FontWeight.Bold,
overflow = TextOverflow.Ellipsis,
)
},
backgroundColor = colors.background,

navigationIcon = {
if (canNavigateBack) {
IconButton(onClick = navigateUp) {
Icon(
imageVector = Icons.Default.ArrowBack,
contentDescription = stringResource(id = R.string.back_button)
)
}
}
/**
IconButton(onClick = { onMenuClick() }) {
Icon(
imageVector = Icons.Default.Menu,
contentDescription = "Menu Icon"
)
}*/
},

)


colors = TopAppBarDefaults.topAppBarColors(
containerColor = MaterialTheme.colors.surfaceColorAtElevation(3.dp)


}
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun GameApp(
    modifier: Modifier = Modifier,

   //top navigation:
   viewModel: GameViewModel = viewModel(),
   // navController: NavHostController = rememberNavController()

) {

   // val backStackEntry by navController.currentBackStackEntryAsState()
   // val currentScreen = NavigationScreens.valueOf(
    //    backStackEntry?.destination?.route ?: NavigationScreens.ListScreen.name
    //)


    //BOTTOM NAVIGATION:

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val  scope = rememberCoroutineScope()


    //THIS IS BOTTOM BAR TOP LEVEL DESTINATION:
    val items = listOf(
        Pantalla1,
        Pantalla2,
        Pantalla3,
        Pantalla4,
        Pantalla5,

       )

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier.fillMaxSize(),

        bottomBar = {BottomBar(navController = navController, items = items)}
/**
        topBar = {
            //TOP BAR COMPOSABLE CUSTOM TO NAVIGATE:
            CustomTopBar(
                //currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }*/

        //BOTTOM NAVIGATION:

        //  modifier = modifier
        //    .fillMaxSize()
        //     .nestedScroll(scrollBehavior.nestedScrollConnection),




    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = colorScheme.background,
        ) {

            GameNavHost(
                navController = navController,
                modifier = modifier
            )
        }
    }
}
/**
@Composable
fun CustomTopBar(
    //TopApp Bar navigation:
   // currentScreen: NavigationScreens,
   // canNavigateBack: Boolean,
    //navigateUp: () -> Unit,


    modifier: Modifier = Modifier
) {

    TopAppBar(

        title = { Text(stringResource(R.string.app_name)) },
        contentColor = colorScheme.inverseSurface,
        backgroundColor = colorScheme.surface,
        /**navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },*/
        modifier = modifier,
        )
}
*/
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
    //back = colorScheme.background,
    androidx.compose.material3.BottomAppBar() {
        BottomNavigation(
            backgroundColor = colorScheme.surface,
            contentColor = colorScheme.inverseSurface,
        ) {
            val currentRoute = currentRoute(navController = navController)
            items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint= Color.Gray,
                            )
                    },
//If show tittle, add this:
                    label = {
                        Text( text = item.title)
                        colorScheme.inverseSurface
                            },
                    alwaysShowLabel = false
                )
            }
        }
    }
}
/**
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}*/