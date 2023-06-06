package com.example.games.model

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    /**
    val developer: String,
    val freetogame_profile_url: String,
    val game_url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val release_date: String,
     */
    val id: Int,
    val title: String,
    val short_description: String,
    val thumbnail: String,

    //adding properties value not fetched from api rest

    var rating: Float = -10f,

    var isFavorite: Boolean = false,
    var isPlayed: Boolean = false,
    var isShared: Boolean = false

)

