package com.example.blockbuster.data.network

import com.example.blockbuster.domain.entities.MoviesFirstResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular?api_key=268eca7ce3ecdeb900e477ba297a3587")
    suspend fun fetchMovies(@Query("page") page: Int): Response<MoviesFirstResponse>
}