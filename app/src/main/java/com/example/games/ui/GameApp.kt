package com.example.games.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.games.AppDestinations.BottomBarScreen
import com.example.games.AppDestinations.BottomBarScreen.*
import com.example.games.GameNavHost
import com.example.games.ui.screens.HomeScreen

//import com.example.games.GameNavHost



@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun TopBar(
    //onMenuClick: () -> Unit,
    //currentScreen: ,
    //canNavigateBack: Boolean,
    //onNavigateUpClicked: () -> Unit
    modifier: Modifier = Modifier,

) {
    /**onMenuClick: () */

    TopAppBar(
        title = {

            Text(
                //text = currentScreen.title,
                text = "Games",
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
            )
        },

        backgroundColor = colors.background
    )
       /*
        navigationIcon = {

            if (canNavigateBack) {
            IconButton(
            onClick = onNavigateUpClicked
            ) {
            Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "btn_try_again"
            )
            }
            }


            IconButton(onClick = { onMenuClick() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu Icon"
                )
            }

        },
*/

     // colors = TopAppBarDefaults.topAppBarColors(
    //  containerColor = MaterialTheme.colors.surfaceColorAtElevation(3.dp)



}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameApp(
    modifier: Modifier = Modifier
) {

    val gameViewModel: GameViewModel =
        viewModel(factory = GameViewModel.Factory)
    HomeScreen(
        gameUiState = gameViewModel.gameUiState,
        retryAction = gameViewModel::getGames,
        modifier = modifier,
    )

    val scaffoldState = rememberScaffoldState()

    val navController = rememberNavController()

    val  scope = rememberCoroutineScope()

    val items = listOf(
        //Pantalla1,
        Pantalla6,
        Pantalla7,
        Pantalla2,
        Pantalla3,
        Pantalla4,
       // Pantalla5,



    )

    Scaffold(
        scaffoldState = scaffoldState,
      //  modifier = modifier
       //    .fillMaxSize()
       //     .nestedScroll(scrollBehavior.nestedScrollConnection),


/**
        topBar = {
            TopBar(
              //  onMenuClick = { /**NOT IMPLEMENTED YET */ },
               // currentScreen = currentScreen,
                //canNavigateBack = canNavigateBack,
               // onNavigateUpClicked = { navController.navigateUp() },


                )
        }
    )*/


       bottomBar = { BottomBar(navController = navController,items) }

    ) {
        GameNavHost(navController)
    }
}

@Composable
fun currentRoute(navController: NavHostController): String?{
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}

@Composable
fun BottomBar(
    navController: NavHostController,
    items: List<BottomBarScreen>,
    modifier: Modifier = Modifier,
) {
    //modifier.background(color = MaterialTheme.colorScheme.background)
    androidx.compose.material3.BottomAppBar() {
        BottomNavigation(
                backgroundColor = MaterialTheme.colors.background
        ) {
            val currentRoute = currentRoute(navController = navController)
            items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.route,
                    onClick = { navController.navigate(item.route)
                        //DOCUMENTATION:

                        //END DOCUMENTATION
                              },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,

                        )
                    },
                    //If show tittle, add this:
                     label = { Text(text = item.title)},
                    alwaysShowLabel = false
                )
            }
        }
    }
}


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
                popUpTo(navController.graph.findStartDestination().id){
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}


/**
@Preview(showBackground = true)
@Composable
fun GameAppPreview() {

}
*/
