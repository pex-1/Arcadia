package com.example.arcadia.core.network

import com.example.arcadia.core.network.game.GameResponse
import com.example.arcadia.core.network.gamedetails.GameDetailsDto
import com.example.arcadia.core.network.genre.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArcadiaApi {

    @GET("genres")
    suspend fun getGenres(): GenreResponse

    @GET("games")
    suspend fun getGames(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("genres") genres: String
    ): GameResponse

    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Int
    ): GameDetailsDto


}