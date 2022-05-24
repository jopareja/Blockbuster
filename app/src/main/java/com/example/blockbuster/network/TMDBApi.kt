package com.example.blockbuster.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.themoviedb.org/3/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    //suspend so the method getPopularMovies gets called in a coroutine
    @GET("movie/popular?api_key=268eca7ce3ecdeb900e477ba297a3587")
    suspend fun getPopularMovies(): Response<MoviesFirstResponse>
}

// My Api will use Retrofit's services
object Api {
    val retrofitService : ApiService by lazy { retrofit.create(ApiService::class.java) }
}