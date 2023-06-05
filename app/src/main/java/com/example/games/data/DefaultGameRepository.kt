package com.example.games.data

import com.example.games.model.Game
import com.example.games.network.GameApiService

class DefaultGameRepository(
    private val gameApiService: GameApiService
): GameRepository {
    override suspend fun getGames(): List<Game> {
        return gameApiService.getGames()
    }
}