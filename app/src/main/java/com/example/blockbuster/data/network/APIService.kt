package com.example.blockbuster.data.network

import com.example.blockbuster.domain.entities.MoviesFirstResponse
import com.example.blockbuster.domain.entities.RatingResponse
import com.example.blockbuster.domain.entities.UserRatingRequest
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @GET("movie/popular?api_key=268eca7ce3ecdeb900e477ba297a3587")
    suspend fun fetchMovies(@Query("page") page: Int): Response<MoviesFirstResponse>

    @POST("movie/{movie_id}/rating?api_key=268eca7ce3ecdeb900e477ba297a3587")
    @Headers("Content-Type:application/json;charset=utf-8")
    suspend fun rateMovie(@Path("movie_id") movieId: Int, @Body requestBody: UserRatingRequest)
    : RatingResponse
}