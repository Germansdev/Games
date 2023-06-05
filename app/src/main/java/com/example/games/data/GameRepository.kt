package com.example.games.data

import com.example.games.model.Game

interface GameRepository {
    suspend fun getGames(): List<Game>
}