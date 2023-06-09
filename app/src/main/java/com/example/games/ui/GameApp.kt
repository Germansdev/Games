package com.example.games.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.games.R
import com.example.games.ui.screens.HomeScreen

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
}


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
    modifier: Modifier = Modifier
) {



    /**
    val viewModel: GameViewModel = viewModel()

    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = NavigationScreens.valueOf(
    backStackEntry?.destination?.route ?: NavigationScreens.ListScreen.name
    )
     */


    //BOTTOM NAVIGATION:
    /**

    val scaffoldState = rememberScaffoldState()
    // val navController = rememberNavController()
    val  scope = rememberCoroutineScope()


    //THIS IS BOTTOM BAR TOP LEVEL DESTINATION:
    val items = listOf(
    //Pantalla1,
    Pantalla6,
    Pantalla7,
    Pantalla2,
    Pantalla3,
    Pantalla4,
    // Pantalla5,
    )

     */
    Scaffold(
       modifier = modifier.fillMaxSize(),

        topBar = {
            CustomTopBar(

            )
            //SIMPLE TOP BAR NO CUSTOMIZED:
/**
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                //backgroundColor = Color.Black


            )*/

            //TOP BAR COMPOSABLE CUSTOM TO NAVIGATE:
            /**
            TopBar(
            canNavigateBack = navController.previousBackStackEntry != null,
            navigateUp = { navController.navigateUp() },
            // onMenuClick = { /**NOT IMPLEMENTED YET */ },
            currentScreenTitle = currentScreen.tittle,
            //onNavigateUpClicked = { navController.navigateUp() },
            )*/


        }

        //BOTTOM NAVIGATION:
        // scaffoldState = scaffoldState,
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

            val gameViewModel: GameViewModel =
                viewModel(factory = GameViewModel.Factory)
            HomeScreen(
                gameUiState = gameViewModel.gameUiState,
                retryAction = gameViewModel::getGames,
                modifier = modifier,
            )
            // }


            //val games by viewModel.games

            /**

            // TODO: Navigation host
            NavHost(
            navController = navController,
            startDestination = NavigationScreens.HomeScreen.name,
            modifier = modifier.padding(innerPadding)
            ) {


            composable( route = NavigationScreens.HomeScreen.name){

            HomeScreen(
            gameUiState = viewModel.gameUiState,
            retryAction = viewModel::getGames,
            modifier = modifier,
            )

            GameListScreen(games = games)
            }


            }
             */
        }
    }



    //BOTTOM NAVIGATION:
    // bottomBar = { BottomBar(navController = navController,items) }

    //) {
    //BOTTOM NAVIGATION:
    //  GameNavHost(navController)
}
@Composable
fun CustomTopBar (
    modifier: Modifier = Modifier
){
    //modifier.background(MaterialTheme.colors.background)
    TopAppBar(
        title = { Text(
            stringResource(R.string.app_name)
        )
                },

        contentColor = colorScheme.inverseSurface,
        backgroundColor = colorScheme.surface


    )
}


//BOTTOM NAVIGATION:
/**
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
}*/