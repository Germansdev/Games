package com.example.games.data

import com.example.games.model.Game
import kotlinx.coroutines.flow.Flow

//Replace the GameRepository previous for DI fetching data:

    class OfflineItemsRepository(private val itemDao: GameDao) : ItemsRepository {
//with original codelab with Flow:
        override fun getAllItemsStream(): Flow<List<Game>> = itemDao.getAllItems()

        //changed to Simple List:
       //override fun getAllItemsStream(): List<Game> = itemDao.getAllItems()

//with original codelab with Flow:
        //override fun getItemStream(id: Int): Flow<Game?> = itemDao.getItem(id)

        //changed to Simple :
        override fun getItemStream(id: Int): Game? = itemDao.getItem(id)

        override suspend fun insertItem(item: Game) = itemDao. insert(item)
//yo ad insertAll_
        override suspend fun insertAll(items: ArrayList<Game>) = itemDao. insertAll(items)

        override suspend fun deleteItem(item: Game) = itemDao.delete(item)

        override suspend fun updateItem(item: Game) = itemDao.update(item)
    }

