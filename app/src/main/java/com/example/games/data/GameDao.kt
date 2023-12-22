package com.example.games.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Update
import androidx.room.Upsert
import com.example.games.model.Game
import com.example.games.model.GameEntity
import com.example.games.model.NetworkGame

// 06/12: import com.example.games.model.Genre


import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Game database
 */
@Dao
interface GameDao {
//SQL for entity tableName "items" /data class Item:
//19/12: anulo todos los metodos con base Game y reemplazo con base GameEntity

    //MVMMTODO:
    @Query("SELECT * from items WHERE title LIKE '%'||:searchQuery ||'%' ORDER BY title ASC")
    fun getSearch(searchQuery: String): Flow<List<Game>>

    @Query("SELECT * from items WHERE title LIKE :searchQuery")
    fun searchItemsByName(searchQuery: String): List<Game>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM items WHERE title MATCH :query")
    fun searchAllGames(query: String): Flow<List<Game>>

    @Query("SELECT * from items ORDER BY title ASC")
    //original codelab with Flow:
    fun getAllItems(): Flow<List<GameEntity>>

    @Query("SELECT * from items WHERE id = :id")
    //original codelab with Flow:
    fun getItem(id: Int): Flow<Game>
/** 06/12:
    /**
     * Generate list of [genre] to show in lazzy row:
     */
    @Query("select distinct genre from items")
    fun getCategories (): Flow<List<Genre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: List<Genre>)
*/
    @Query("select * from items where genre = :gameGenre")
    fun getGamesByCategory (gameGenre: String): Flow<List<Game>>

    @Query("SELECT * FROM items WHERE isFavorite = 1")
    //original codelab with Flow:
    fun getAllFavorites( /**isFavorite: Boolean*/ ): Flow<List<Game>>

    @Query("SELECT * FROM items WHERE isPlayed = 1")
    //original codelab with Flow:
    fun getAllPlayed(): Flow<List<Game>>

    @Query("SELECT * FROM items WHERE isPlayed = 0")
    //original codelab with Flow:

    fun getAllNotPlayed(): Flow<List<Game>>

    @Query("SELECT * FROM items WHERE isShared = 1")
    fun getAllShared(): Flow<List<Game>>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert( item: Game)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // 12/12: suspend fun insertAll(items: ArrayList<Game>)
    suspend fun insertAll(items: ArrayList<Game>)//: List<Long>
    @Upsert
    suspend fun upsertAll(items: ArrayList<Game>)

    @Update
    suspend fun update(item: Game)

   // @Delete
   // suspend fun delete(item: Game)

    @Upsert
    suspend fun upsertAllItemsStream(itemEntity: ArrayList<Game>)
}

/**
    /**
     * 19/12: pongo la GameEntity del modelo:
     *
     */
    @Query("SELECT * from items WHERE title LIKE '%'||:searchQuery ||'%' ORDER BY title ASC")
    fun getSearch(searchQuery: String): Flow<List<GameEntity>>

    @Query("SELECT * from items WHERE title LIKE :searchQuery")
    fun searchItemsByName(searchQuery: String): List<GameEntity>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM items WHERE title MATCH :query")
    fun searchAllGames(query: String): Flow<List<GameEntity>>

    @Query("SELECT * from items ORDER BY title ASC")
    //original codelab with Flow:
    fun getAllItems(): Flow<List<GameEntity>>

    @Query("SELECT * from items WHERE id = :id")
    //original codelab with Flow:
    fun getItem(id: Int): Flow<GameEntity>
    /** 06/12:
    /**
     * Generate list of [genre] to show in lazzy row:
    */
    @Query("select distinct genre from items")
    fun getCategories (): Flow<List<Genre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenre(genre: List<Genre>)
     */
    @Query("select * from items where genre = :gameGenre")
    fun getGamesByCategory (gameGenre: String): Flow<List<GameEntity>>

    @Query("SELECT * FROM items WHERE isFavorite = 1")
    //original codelab with Flow:
    fun getAllFavorites( /**isFavorite: Boolean*/ ): Flow<List<GameEntity>>

    @Query("SELECT * FROM items WHERE isPlayed = 1")
    //original codelab with Flow:
    fun getAllPlayed(): Flow<List<GameEntity>>

    @Query("SELECT * FROM items WHERE isPlayed = 0")
    //original codelab with Flow:

    fun getAllNotPlayed(): Flow<List<GameEntity>>

    @Query("SELECT * FROM items WHERE isShared = 1")
    fun getAllShared(): Flow<List<GameEntity>>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert( item: GameEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    // 12/12: suspend fun insertAll(items: ArrayList<Game>)
    suspend fun insertAll(
        items:/** Array*/
       List<GameEntity>
    )//: List<Long>

    @Update
    suspend fun update(item: GameEntity)

    // @Delete
    // suspend fun delete(item: Game)
    @Upsert
    suspend fun upsertAll(items: List<GameEntity>)

    @Upsert
    suspend fun upsertAllItemsStream(itemEntity: List<GameEntity>)
    //fin 19/12
}
        */