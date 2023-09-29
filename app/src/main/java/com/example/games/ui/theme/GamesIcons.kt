package com.example.games.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Games
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Games
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Rocket
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.ui.graphics.vector.ImageVector


object GamesIcons {
    val Search = Icons.Rounded.Search
    val Favorites = Icons.Rounded.Favorite
    val FavoritesBorder = Icons.Outlined.FavoriteBorder
    val Settings = Icons.Rounded.Settings
    val Explore = Icons.Rounded.Rocket
    val ArrowBack  = Icons.Rounded.ArrowBack
    val Close = Icons.Rounded.Close
    val Play = Icons.Rounded.Games
    val PlayBorder = Icons.Outlined.Games
    val BarChart = Icons.Rounded.BarChart
    val BarChartBorder = Icons.Outlined.BarChart
    val Share = Icons.Rounded.Share
    val ShareBorder = Icons.Outlined.Share
    val Home = Icons.Rounded.Home
    val HomeBorder = Icons.Outlined.Home

}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}