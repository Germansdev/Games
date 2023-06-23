package com.example.games.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val short_description: String,
    val thumbnail: String,

    val developer: String,
    val game_url: String,
    val freetogame_profile_url: String,
    val genre: String,
    val platform: String,
    val publisher: String,
    val release_date: String,

    var rating: Float = -10f,
    var isFavorite: Boolean = false,
    var isPlayed: Boolean = false,
    var isShared: Boolean = false
)
*/