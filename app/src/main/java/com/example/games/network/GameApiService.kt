package com.example.games.network

import com.example.games.model.Game

import retrofit2.http.GET


/**
 * A public interface that exposes the [getGames] method FETCH GAMES
 */
interface GameApiService {
    companion object {
        const val BASE_URL = "https://www.freetogame.com/api/"
    }

    /**
     * Returns a [List] of [Game] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "games" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("games")
    suspend fun getGames(): ArrayList<Game>
    {        return getGames()    }
}





