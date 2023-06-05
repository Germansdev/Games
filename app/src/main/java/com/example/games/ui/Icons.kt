package com.example.games.ui

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Games
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.Fullscreen
import androidx.compose.material.icons.rounded.Games
import androidx.compose.material.icons.rounded.Grid3x3
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShortText
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material.icons.rounded.ViewDay
import androidx.compose.material.icons.rounded.VolumeOff
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.games.R

//NIA FOR USE ICONS DRAWABLE AND ICONS GENERATE VAL :
object GamesIcons {
    val StarRate = R.drawable.baseline_star_24
    val StarRateBorder = R.drawable.baseline_star_outline_24
    val StarDrawBorder = R.drawable.baseline_star_outline_24
    val StarDraw = R.drawable.baseline_star_24
    val Share = R.drawable.baseline_share_24
    val ControlGame = R.drawable.baseline_games_24
    val Search = R.drawable.baseline_search_24
    val FavoritesBorder = R.drawable.baseline_favorite_border_24
    val Favorites = R.drawable.baseline_favorite_border_24
    val GameRounded = Icons.Rounded.Games
    val GameBorder = Icons.Outlined.Games
    val GameBorderDraw = R.drawable.round_games_24
    val GameRoundedDraw = R.drawable.baseline_games_24
    val Home = R.drawable.baseline_home_24
    val HomeOutlined = R.drawable.outline_home_24
    val AccountCircle = Icons.Outlined.AccountCircle
    val Add = Icons.Rounded.Add
    val ArrowBack = Icons.Rounded.ArrowBack
    val ArrowDropDown = Icons.Default.ArrowDropDown
    val ArrowDropUp = Icons.Default.ArrowDropUp
    val Bookmark = R.drawable.ic_bookmark
    val BookmarkBorder = R.drawable.ic_bookmark_border
    val Bookmarks = R.drawable.ic_bookmarks
    val BookmarksBorder = R.drawable.ic_bookmarks_border
    val Check = Icons.Rounded.Check
    val Close = Icons.Rounded.Close
    val ExpandLess = Icons.Rounded.ExpandLess
    val Fullscreen = Icons.Rounded.Fullscreen
    val Grid3x3 = Icons.Rounded.Grid3x3
    val MenuBook = R.drawable.ic_menu_book
    val MenuBookBorder = R.drawable.ic_menu_book_border
    val MoreVert = Icons.Default.MoreVert
    val Person = Icons.Rounded.Person
    val PlayArrow = Icons.Rounded.PlayArrow

    val Settings = Icons.Rounded.Settings
    val ShortText = Icons.Rounded.ShortText
    val Tag = Icons.Rounded.Tag
    val Upcoming = R.drawable.ic_upcoming
    val UpcomingBorder = R.drawable.ic_upcoming_border
    val ViewDay = Icons.Rounded.ViewDay
    val VolumeOff = Icons.Rounded.VolumeOff
    val VolumeUp = Icons.Rounded.VolumeUp
}

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class  DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}