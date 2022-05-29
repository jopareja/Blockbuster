package com.example.blockbuster.data.network

import com.example.blockbuster.data.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
// Service that will get called by the MovieRepository. This will call TheMovieDataBaseApiClient
class RetrofitService {

    private val baseUrl = "https://api.themoviedb.org/3/"

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(baseUrl).build()

    suspend fun getMovies(noPage : Int) : List<Movie> {

        return withContext(Dispatchers.IO) {
            val response = retrofit.create(MoviesApiClient::class.java).getPopularMovies(page = noPage)
            response.body()?.results ?: emptyList()
        }

    }
}