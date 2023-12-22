package com.example.games.data

//import com.example.games.model.Genre

import com.example.games.model.Game
import com.example.games.model.GameEntity
import com.example.games.model.NetworkGame
import com.example.games.model.asEntity
import com.example.games.model.asExternalModel
import com.example.games.network.GameApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext

//Replace the GameRepository previous for DI fetching data:

class OfflineItemsRepository(

    private val itemDao: GameDao,
    private val apiService: GameApiService,
    //19/12: agrego: GameNetworkDataSource
   // private val network: GameNetworkDataSource
    //19/12: meto Game

    ) : ItemsRepository {

    /**
     * 19/12: anulo todos los override base Game y reemplazo abajo on base GameEntity
     */

    /** 19/12 elimino*/
    override suspend fun searchItemsByName(searchQuery: String): List<Game> {
        return withContext(Dispatchers.IO) {
            itemDao.searchItemsByName(searchQuery)
        }
    }

    override fun getSearchItemsStream(searchQuery: String): Flow<List<Game>> =
        itemDao.getSearch(searchQuery)

    //.map {it.map(GameEntity::asExternalModel) }
    override fun searchAllGamesStream(query: String): Flow<List<Game>> =
        itemDao.searchAllGames(query)
    //.map {it.map(GameEntity::asExternalModel) }
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX:

    override fun getAllItemsStream(): Flow<List<Game>> = itemDao.getAllItems()
        .map { it.map(GameEntity::asExternalModel) }


    /** 06/12:
    override fun getCategories(): Flow<List<Genre>> = itemDao.getCategories()

    //  override fun getCategories(): Flow<List<Genre>> = itemDao.getCategories()

    override suspend fun insertGenreStream(genre: Flow<List<Genre>>) //= itemDao.insertGenre(listOf())
    {
    return withContext(Dispatchers.IO) {
    val cate = itemDao.getCategories()
    //itemDao.insertGenre(listOf())
    cate.apply { itemDao.insertGenre(listOf()) }
    }
    }
     */

    override fun getGamesByCategoryStream(gameGenre: String): Flow<List<Game>> =
        itemDao.getGamesByCategory(gameGenre)
    // .map {it.map(GameEntity::asExternalModel) }

    override fun getItemStream(id: Int): Flow<Game?> = itemDao.getItem(id)

    override fun getAllFavoritesStream(isFavorite: Boolean): Flow<List<Game?>> =
        itemDao.getAllFavorites()

    // .map {it.map(GameEntity::asExternalModel) }
    override fun getAllPlayedStream(isPlayed: Boolean): Flow<List<Game?>> = itemDao.getAllPlayed()

    // .map {it.map(GameEntity::asExternalModel) }
    //XXXXXXXXXXXXXXXXXXXXXXXXX:
    override fun getAllNotPlayedStream(isPlayed: Boolean): Flow<List<Game?>> =
        itemDao.getAllNotPlayed()
    //  .map {it.map(GameEntity::asExternalModel) }


    override fun getAllSharedStream(isShared: Boolean): Flow<List<Game?>> = itemDao.getAllShared()
    //  .map {it.map(GameEntity::asExternalModel) }
    //19/12elimino:
    //override suspend fun insertItem(item: Game) = itemDao.insert(item)

    //19/12 anulo el siguiente override y agrego el otro:
    override suspend fun insertAll(items: ArrayList<Game>) {
    return withContext(Dispatchers.IO) {
    itemDao.insertAll(apiService.getGames())
    }
    }


//19/12 elimino:
    /**
    override suspend fun insertAll(items: ArrayList<Game>) {
    return withContext(Dispatchers.IO) {
    itemDao.insertAll(apiService.getGames())
    }
    }*/


    //  override suspend fun deleteItem(item: Game) = itemDao.delete(item)
    override suspend fun updateItem(item: Game) = itemDao.update(item)
}



    /**
     * 19/12 acá reemplazo el original:

*/
    /**
    override suspend fun searchItemsByName(searchQuery: String): List<Game> {
        return withContext(Dispatchers.IO) {
        itemDao.searchItemsByName(searchQuery)
            .map(GameEntity::asExternalModel)
        }
    }

    //19/12:
    override fun getSearchItemsStream(searchQuery: String): Flow<List<GameEntity>> =
        itemDao.getSearch(searchQuery)

    override fun searchAllGamesStream(query: String): Flow<List<GameEntity>> =
        itemDao.searchAllGames(query)
     */
/**
    override suspend fun getAllItemsStream(networkGame: List<NetworkGame>) {
        return withContext(Dispatchers.IO) {
            val items = network.getGames()//apiService.getGames()
                .map(NetworkGame::asEntity)

            itemDao.insertAll( items )

            itemDao.getAllItems()
                .map { it.map(GameEntity::asExternalModel) }


        }.collect()
    /**    return withContext(Dispatchers.IO) {
          val items=apiService.getGames().map { NetworkGame::asEntity }
            itemDao.insertAll(items = listOf())
            itemDao.getAllItems()
                .map {it.map(GameEntity::asExternalModel)}
        }*/

        //return flowOf(listOf())
    }
   // = itemDao. getAllItems()
     //  .map {it.map(GameEntity::asExternalModel) }


   // */


    /** 06/12:
    override fun getCategories(): Flow<List<Genre>> = itemDao.getCategories()

    //  override fun getCategories(): Flow<List<Genre>> = itemDao.getCategories()

    override suspend fun insertGenreStream(genre: Flow<List<Genre>>) //= itemDao.insertGenre(listOf())
    {
    return withContext(Dispatchers.IO) {
    val cate = itemDao.getCategories()
    //itemDao.insertGenre(listOf())
    cate.apply { itemDao.insertGenre(listOf()) }
    }
    }


    override fun getGamesByCategoryStream(gameGenre: String): Flow<List<Game>> =
        itemDao.getGamesByCategory(gameGenre)
            .map {it.map(GameEntity::asExternalModel) }

    override fun getItemStream(id: Int): Flow<Game?> = itemDao.getItem(id)
        .map(GameEntity::asExternalModel)

    override fun getAllFavoritesStream(isFavorite: Boolean): Flow<List<Game?>> =
        itemDao.getAllFavorites()
            .map {it.map(GameEntity::asExternalModel) }
    override fun getAllPlayedStream(isPlayed: Boolean): Flow<List<Game?>>
    = itemDao.getAllPlayed()
        .map {it.map(GameEntity::asExternalModel) }
    override fun getAllNotPlayedStream(isPlayed: Boolean): Flow<List<Game?>> =
        itemDao.getAllNotPlayed()
            .map {it.map(GameEntity::asExternalModel) }
    override fun getAllSharedStream(isShared: Boolean): Flow<List<Game?>> =
        itemDao.getAllShared()
            .map {it.map(GameEntity::asExternalModel) }

    override suspend fun insertItem(item: GameEntity) = itemDao.insert(item)
   // override suspend fun insertAll(items: ArrayList<GameEntity>) =itemDao.insertAll(items)
    //19/12 anulo el siguiente override y agrego el otro:
    /**  override suspend fun insertAll(items: ArrayList<Game>) {
    return withContext(Dispatchers.IO) {
    itemDao.insertAll(apiService.getGames())
    }
    }*/
    override suspend fun insertAll(items: /**Array*/List<NetworkGame>) {
        return withContext(Dispatchers.IO) {
            items.map {(NetworkGame::asEntity)}
            itemDao.insertAll(items = ArrayList())
        }

                /**apiService.getGames(NetworkGame)*/
         /**   items.map {
            items = itemDao.insertAll(GameEntity)
                itemDao.insertAll(NetworkGame::asEntity)
            }*/
            //itemDao.insertAll(ArrayList() )
        }




    //  override suspend fun deleteItem(item: Game) = itemDao.delete(item)
    override suspend fun updateItem(item: Game) =
        itemDao.update(GameEntity())
           // .map {it.map(GameEntity::asExternalModel) }

    override suspend fun upsertItemsStream(itemEntity: List<GameEntity>) =
        itemDao.upsertAll(itemEntity)




    //no tocar esto siguiente:
    override suspend fun syncWith(/**synchronizer: Synchronizer*/): Boolean {
        val networkGame = network.getGames(ids = null)

        itemDao.upsertAll(
            items = networkGame.map(
                NetworkGame::asEntity,
            )
        )
      /**  itemDao.insertAll(
            items = networkGame.map {
                NetworkGame::asEntity
            }
        )*/


        return true
    }


}
     */

