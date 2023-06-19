package com.example.games

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable


const val searchRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(
    onBackClick: () -> Unit,

) {
    NavController.addNavigator()
    // TODO: Handle back stack for each top-level destination. At the moment each top-level
    // destination may have own search screen's back stack.
    composable(route = searchRoute) {
       SearchRoute(
            onBackClick = onBackClick,

        )
    }

}

private fun NavController.Companion.addNavigator() {

}

@Composable
internal fun SearchRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    SearchScreen()
}

@Composable
fun SearchScreen() {
    TODO("Not yet implemented")
}


