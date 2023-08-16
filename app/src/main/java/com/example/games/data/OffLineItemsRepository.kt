package com.example.games.data

import com.example.games.model.Game
import com.example.games.model.asExternalModel
import com.example.games.network.GameApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

//Replace the GameRepository previous for DI fetching data:

    class OfflineItemsRepository(
        private val itemDao: GameDao,
        private val apiService: GameApiService
        ) : ItemsRepository {
        override suspend fun searchItemsByName(searchQuery: String): List<Game> {
            return withContext(Dispatchers.IO){
                itemDao.searchItemsByName(searchQuery)
            }
        }

        override fun getSearchItemsStream(searchQuery: String): Flow<List<Game>> = itemDao.getSearch(searchQuery )
        override fun searchAllGamesStream(query: String): Flow<List<Game>> = itemDao.searchAllGames(query)


        //with original codelab with Flow:
        override fun getAllItemsStream(): Flow<List<Game>> = itemDao.getAllItems()
    .map {
            it.map (Game::asExternalModel) }

        //changed to Simple List:
       //override fun getAllItemsStream(): List<Game> = itemDao.getAllItems()

//with original codelab with Flow:
        override fun getItemStream(id: Int): Flow<Game?> = itemDao.getItem(id)

        override fun getAllFavoritesStream(isFavorite: Boolean): Flow<List<Game?>>
        = itemDao.getAllFavorites()

        override fun getAllPlayedStream(isPlayed: Boolean): Flow<List<Game?>> = itemDao.getAllPlayed()

        override fun getAllNotPlayedStream(isPlayed: Boolean): Flow<List<Game?>> = itemDao.getAllNotPlayed()
        override fun getAllSharedStream(isShared: Boolean): Flow<List<Game?>> = itemDao.getAllShared()

        //changed to Simple :
       // override fun getItemStream(id: Int): Game? = itemDao.getItem(id)

        override suspend fun insertItem(item: Game) = itemDao. insert(item)
//yo ad insertAll_
        override suspend fun insertAll(items: ArrayList<Game>) = itemDao. insertAll(items)

        override suspend fun deleteItem(item: Game) = itemDao.delete(item)

        override suspend fun updateItem(item: Game) = itemDao.update(item)

    }

