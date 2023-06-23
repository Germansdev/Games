package com.example.games.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.games.network.GameApiService
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


fun Game.asEntity() = Game (
    developer= developer,
    game_url = game_url,
    freetogame_profile_url = freetogame_profile_url,
    genre = genre,
    platform = platform,
    publisher = publisher,
    release_date = release_date,
    id = id,
    title = title,
    short_description = short_description,
    thumbnail = thumbnail

)
fun Game.asExternalModel() = Game(
    developer= developer,
    game_url = game_url,
    freetogame_profile_url = freetogame_profile_url,
    genre = genre,
    platform = platform,
    publisher = publisher,
    release_date = release_date,
    id = id,
    title = title,
    short_description = short_description,
    thumbnail = thumbnail
)