package com.example.blockbuster.data

import com.example.blockbuster.data.network.RetrofitService

// The Repo that will decide to get the data with a RetrofitService or other XService.
class MovieRepository {

    private val api = RetrofitService()

    suspend fun getPopularMovies(noPage: Int) : List<Movie> {

        val response = api.getMovies(noPage)
        return response
    }
}
