package com.example.games.data

import com.example.games.model.Game
import com.example.games.model.GameEntity
import com.example.games.model.Genre
import com.example.games.model.asExternalModel

import com.example.games.network.GameApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

//Replace the GameRepository previous for DI fetching data:

class OfflineItemsRepository(

    private val itemDao: GameDao,
    private val apiService: GameApiService,

    ) : ItemsRepository {
    override suspend fun searchItemsByName(searchQuery: String): List<Game> {
        return withContext(Dispatchers.IO) {
            itemDao.searchItemsByName(searchQuery)   }
    }
    override fun getSearchItemsStream(searchQuery: String): Flow<List<Game>> =
        itemDao.getSearch(searchQuery)

    override fun searchAllGamesStream(query: String): Flow<List<Game>> =
        itemDao.searchAllGames(query)

    override fun getAllItemsStream(): Flow<List<Game>> = itemDao. getAllItems()
        .map {it.map(GameEntity::asExternalModel) }

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

    override fun getItemStream(id: Int): Flow<Game?> = itemDao.getItem(id)

    override fun getAllFavoritesStream(isFavorite: Boolean): Flow<List<Game?>> =
        itemDao.getAllFavorites()
    override fun getAllPlayedStream(isPlayed: Boolean): Flow<List<Game?>> = itemDao.getAllPlayed()
    override fun getAllNotPlayedStream(isPlayed: Boolean): Flow<List<Game?>> =
        itemDao.getAllNotPlayed()
    override fun getAllSharedStream(isShared: Boolean): Flow<List<Game?>> = itemDao.getAllShared()
    override suspend fun insertItem(item: Game) = itemDao.insert(item)
    override suspend fun insertAll(items: ArrayList<Game>) {
        return withContext(Dispatchers.IO) {
            itemDao.insertAll(apiService.getGames())
        }
    }




    override suspend fun deleteItem(item: Game) = itemDao.delete(item)
    override suspend fun updateItem(item: Game) = itemDao.update(item)

}

