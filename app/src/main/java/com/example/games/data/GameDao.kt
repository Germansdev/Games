package com.example.games.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.games.model.Game
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Game database
 */
@Dao
interface GameDao {
//SQL for entity tableName "items" /data class Item:
    @Query("SELECT * from items ORDER BY title ASC")
    //original codelab with Flow:
 fun getAllItems(): Flow<List<Game>>

//with List, instead Flow:
 //   fun getAllItems(): List<Game>



    @Query("SELECT * from items WHERE id = :id")
    //original codelab with Flow:
    //fun getItem(id: Int): Flow<Game>

//with List, instead Flow:
    fun getItem(id: Int): Game

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Game)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(items:ArrayList<Game>)

    @Update
    suspend fun update(item: Game)

    @Delete
    suspend fun delete(item: Game)
}