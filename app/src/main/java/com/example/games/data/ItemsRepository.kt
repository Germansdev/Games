package com.example.games.data


import com.example.games.model.Game
import com.example.games.model.GameEntity
import com.example.games.model.NetworkGame
import com.example.games.model.asEntity
import com.example.games.model.asExternalModel
import kotlinx.coroutines.Dispatchers
//import com.example.games.model.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

//19/12 agrego que implementa Syncable y una funcion suspend para poder llamar luego como suspend:

interface ItemsRepository {

//interface ItemsRepository:Syncable {
    /**
     * Retrieve all the items from the the given data source.
     */
    /**
     * 19/12: sustituyo la base original Game por GameEntity:
     */

    //19/12 elimino:
    suspend fun searchItemsByName(searchQuery: String): List<Game>


    //with original from codelab with Flow:

    fun getSearchItemsStream(searchQuery: String): Flow<List<Game>>

    fun searchAllGamesStream(query: String): Flow<List<Game>>

    fun getAllItemsStream(): Flow<List<Game>>

    //without Flow:
    //fun getAllItemsStream(): List<Game>

    /** 06/12:
    fun getCategories():Flow<List<Genre>>
    suspend fun insertGenreStream(genre: Flow<List<Genre>>)
     */
    fun getGamesByCategoryStream(gameGenre: String): Flow<List<Game>>

    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    //with original from codelab with Flow:
    fun getItemStream(id: Int): Flow<Game?>
    //without Flow:
    // fun getItemStream(id: Int):Game?

    fun getAllFavoritesStream(isFavorite: Boolean): Flow<List<Game?>>
    fun getAllPlayedStream(isPlayed: Boolean): Flow<List<Game?>>
    fun getAllNotPlayedStream(isPlayed: Boolean): Flow<List<Game?>>
    fun getAllSharedStream(isShared: Boolean): Flow<List<Game?>>


    /**
     * Insert item in the data source
     */
    //19/12 elimino:   suspend fun insertItem(item: Game)


//19/12 elimino:
suspend fun insertAll(items:ArrayList<Game>)

    /**
     * Delete item from the data source
     */
    //suspend fun deleteItem(item: Game)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: Game)
}

    /**
     * 19/12 acá sustituyo la base Game por GameEntity
*/
    /**
    suspend fun searchItemsByName(searchQuery: String): List<Game>


    //with original from codelab with Flow:
   //19/12: fun getSearchItemsStream(searchQuery: String): Flow<List<GameEntity>>

   //19/12: fun searchAllGamesStream(query: String): Flow<List<GameEntity>>

    suspend fun getAllItemsStream(/**19/12 agrego param*//***/ networkGame: List<NetworkGame>)//: Flow<List<Game>>

    //without Flow:
    //fun getAllItemsStream(): List<Game>
    /** 06/12:
    fun getCategories():Flow<List<Genre>>
    suspend fun insertGenreStream(genre: Flow<List<Genre>>)
     */
    fun getGamesByCategoryStream (gameGenre: String): Flow<List<Game>>

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
    suspend fun insertItem(item: GameEntity)


    suspend fun insertAll(items:/**Array*/List<NetworkGame>)/**:ArrayList<GameEntity>{
        return withContext(Dispatchers.IO) {
            insertAll(items.map { NetworkGame::asEntity }
                //.map (GameEntity::asE)
            )
    }
    }*/

    /**
     * Delete item from the data source
     */
    //suspend fun deleteItem(item: Game)

    /**
     * Update item in the data source
     */
    suspend fun updateItem(item: Game)

    suspend fun upsertItemsStream(itemEntity: List<GameEntity>)


}
        */