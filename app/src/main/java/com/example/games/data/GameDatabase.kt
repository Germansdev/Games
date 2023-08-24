package com.example.games.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.games.model.Game
import com.example.games.model.GameEntity
import com.example.games.model.Genre


@Database(
    entities = [
        Game::class,
        Genre::class,
               ],

    version = 7  , exportSchema = false)

abstract class GameDatabase : RoomDatabase() {

        abstract fun itemDao(): GameDao

        companion object {
            @Volatile
            private var Instance: GameDatabase? = null

            fun getDatabase(context: Context): GameDatabase {
                // if the Instance is not null, return it, otherwise create a new database instance.
                return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, GameDatabase::class.java, "game_database")
                        /**
                         * Setting this option in your app's database builder means that Room
                         * permanently deletes all data from the tables in your database when it
                         * attempts to perform a migration with no defined migration path.
                         */
                        .fallbackToDestructiveMigration()
                        .build()
                        .also { Instance = it }
                }

            }
        }
    }


