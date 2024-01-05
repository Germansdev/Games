package com.example.games.data

import com.example.games.model.Game
import com.example.games.network.GameApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OfflineItemsRepository(

    private val itemDao: GameDao,
    private val apiService: GameApiService,

    ) : ItemsRepository {

    override suspend fun searchItemsByName(searchQuery: String): List<Game> {
        return withContext(Dispatchers.IO) {
            itemDao.searchItemsByName(searchQuery)
        }
    }

    override fun getSearchItemsStream(searchQuery: String): Flow<List<Game>> =
        itemDao.getSearch(searchQuery)

    override fun searchAllGamesStream(query: String): Flow<List<Game>> =
        itemDao.searchAllGames(query)
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX:

    override fun getAllItemsStream(): Flow<List<Game>> = itemDao.getAllItems()

    override fun getGamesByCategoryStream(gameGenre: String): Flow<List<Game>> =
        itemDao.getGamesByCategory(gameGenre)

    override fun getItemStream(id: Int): Flow<Game?> = itemDao.getItem(id)

    override fun getAllFavoritesStream(isFavorite: Boolean): Flow<List<Game?>> = itemDao.getAllFavorites()

    override fun getAllPlayedStream(isPlayed: Boolean): Flow<List<Game?>> = itemDao.getAllPlayed()

    //XXXXXXXXXXXXXXXXXXXXXXXXX:
    override fun getAllNotPlayedStream(isPlayed: Boolean): Flow<List<Game?>> =
        itemDao.getAllNotPlayed()

    override fun getAllSharedStream(isShared: Boolean): Flow<List<Game?>> = itemDao.getAllShared()

    override suspend fun insertAll(items: ArrayList<Game>) {
        return withContext(Dispatchers.IO) {
            itemDao.insertAll(apiService.getGames())
        }
    }

    //  override suspend fun deleteItem(item: Game) = itemDao.delete(item)
    override suspend fun updateItem(item: Game) = itemDao.update(item)
}

