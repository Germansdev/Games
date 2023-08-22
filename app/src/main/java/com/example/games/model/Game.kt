package com.example.games.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

// ORIGINAL: @Entity(tableName = "Items")


/**
 * External data layer representation of a Game
 */
/**
data class Game(
    //  NOT USED YET:
    val developer: String = "",
    val game_url: String = "",
    val freetogame_profile_url: String = "",
    val genre: String = "",
    val platform: String = "",
    val publisher: String = "",
    val release_date: String = "",

    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val short_description: String = "",
    val thumbnail: String = "",

    //adding properties value not fetched from api rest

    var rating: Float = 0f,

    var  countPlayed: Int = 0,

    var isFavorite: Boolean = false,
    var isPlayed: Boolean = false,
    var isShared: Boolean = false,


//nullable properties:

    var played: Int = 0,
    var favorited: Int = 0,
    var shared: Int = 0,

    )

*/


