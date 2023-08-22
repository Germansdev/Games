package com.example.games.data

import com.example.games.model.Game
import com.example.games.network.GameApiService
//Replace GameRepository with ItemRpository

class DefaultGameRepository(
    private val  gameApiService: GameApiService
    //this single line previous only fetch:
): GameRepository {

//this single line with database:
    //): ItemsRepository {
    override suspend fun getGames(): ArrayList<Game> {
        return gameApiService.getGames()
  //  return gameApiService.getLocalGames()
    }
/**
    override suspend fun getCategories(): List<Game> {
     return gameApiService.getCategories()
    }
    */

/**
    override suspend fun getShooterGames(): List<Game> {
      return gameApiService.getShooterGames().games

    }*/


}