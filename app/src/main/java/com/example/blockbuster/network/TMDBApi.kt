package com.example.blockbuster.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "268eca7ce3ecdeb900e477ba297a3587"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String = API_KEY): List<Movie>
}

object Api {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}