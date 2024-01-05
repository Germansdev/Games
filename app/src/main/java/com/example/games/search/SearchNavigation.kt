package com.example.games.search

import androidx.navigation.NavController
import androidx.navigation.NavOptions
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




