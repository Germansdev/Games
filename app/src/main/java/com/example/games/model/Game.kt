package com.example.games.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
@Entity(tableName = "Items")
@Serializable
data class Game(
 //  NOT USED YET:
    val developer: String,
    val game_url: String,
    val freetogame_profile_url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val release_date: String,

    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val short_description: String,
    val thumbnail: String,

    //adding properties value not fetched from api rest

    var rating: Float = -10f,

    var isFavorite: Boolean = false,
    var isPlayed: Boolean = false,
    var isShared: Boolean = false

)

