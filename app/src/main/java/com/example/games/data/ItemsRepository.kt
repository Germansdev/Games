package com.example.games.data

import com.example.games.model.Game
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {
    /**
     * Retrieve all the items from the the given data source.
     */
    //with original from codelab with Flow:

//    fun getSearchItemsStream(searchQuery: String): Flow<List<Game>>

    fun searchAllGamesStream(query: String): Flow<List<Game>>

    fun getAllItemsStream(): Flow<List<Game>>

    //without Flow:
    //fun getAllItemsStream(): List<Game>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    //with original from codelab with Flow:
    fun getItemStream(id: Int): Flow<Game?>
    //without Flow:
   // fun getItemStream(id: Int):Game?

    fun getAllFavoritesStream(isFavorite: Boolean): Flow<List<Game?>>
    fun getAllPlayedStream(isPlayed:Boolean): Flow<List<Game?>>
    fun getAllNotPlayedStream(isPlayed: Boolean): Flow<List<Game?>>
    fun getAllSharedStream(isShared:Boolean): Flow<List<Game?>>


    /**
     * Insert item in the data source
     */
    suspend fun insertItem(item: Game)


    suspend fun insertAll(items:ArrayList<Game>)

    /**
     * Delete item from the data source
     */
    suspend fun deleteItem(item: Game)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: Game)

   // suspend fun selectFavorites ()
}