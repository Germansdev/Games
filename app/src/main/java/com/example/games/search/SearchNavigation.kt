package com.example.games.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.games.R
import com.example.games.appDestinations.NavigationDestination



const val searchRoute = "search_route"

object SearchScreenDestination : NavigationDestination {
    override val route: String = "search_route"
    override val titleRes: Int = R.string.search
}

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

@RequiresApi(Build.VERSION_CODES.S)
fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,

    ) {
    //NavController.addNavigator()
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = searchRoute) {
        SearchRoute(
            onBackClick = onBackClick,
            searchViewModel = viewModel(),
            notPlayedViewModel = viewModel()
        )
    }
}



