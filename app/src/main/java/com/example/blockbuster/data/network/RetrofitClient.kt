package com.example.blockbuster.data.network

import com.example.blockbuster.data.repositories.RemoteInterfacer
import com.example.blockbuster.domain.entities.Movie
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

// Service that will get called by the MovieRepository. This will call ApiService
class RetrofitClient @Inject constructor(private val api: APIService) : RemoteInterfacer {



    override suspend fun getPopularMovies(pageNumber : Int) : List<Movie> {

        return withContext(Dispatchers.IO) {
            val response = api.fetchMovies(page = pageNumber)
            response.body()?.results ?: emptyList()
        }

    }
}