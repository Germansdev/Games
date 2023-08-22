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
//only fetch:
//suspend fun getGames (): List<Game>

//change to ArrayList to database:
    //ORIGINAL :
    suspend fun getGames(): ArrayList<Game>
    {        return getGames()    }

    //with externalModel:
  //  suspend fun getGames(ids:List<String>?): ArrayList<NetworkGame>


    /**   @GET("games?category=shooter")
    suspend fun getCategories(): List<Game>
    */
/**
    @GET("games?category=shooter")
    suspend fun getShooterGames(): GamesResponse
*/
}

data class GamesResponse(val games: List<Game>)

//Yo added these 2 fun: Plants example:







/**
//to get details of one Book:
@GET ("games/{id}")
suspend fun getGame(@Path("id")id:Int): Game?
*/
