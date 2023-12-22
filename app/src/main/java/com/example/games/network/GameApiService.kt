package com.example.games.network

import com.example.games.model.Game
import com.example.games.model.NetworkGame

import retrofit2.http.GET
import retrofit2.http.Query


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
    //19/12 anulo este GET y pongo el de abajo:

    @GET("games")
    suspend fun getGames(): ArrayList<Game>
    //19/12: {        return getGames()    }
    /**
    //19/12:
    @GET("games")
    suspend fun getGames(): ArrayList<NetworkGame>


    @GET("games")
    suspend fun getGames(
        @Query("id") ids:List<String>?,
         ): List<NetworkGame>
    */

}