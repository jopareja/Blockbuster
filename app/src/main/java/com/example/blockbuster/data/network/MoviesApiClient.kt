package com.example.blockbuster.data.network

import com.example.blockbuster.data.MoviesFirstResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiClient {

    @GET("movie/popular?api_key=268eca7ce3ecdeb900e477ba297a3587")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<MoviesFirstResponse>
}