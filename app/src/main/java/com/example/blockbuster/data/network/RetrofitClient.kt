package com.example.blockbuster.data.network

import com.example.blockbuster.data.repositories.RemoteProvider
import com.example.blockbuster.domain.entities.Movie
import com.example.blockbuster.domain.entities.RatingResponse
import com.example.blockbuster.domain.entities.UserRatingRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

// Service that will get called by the MovieRepository. This will call an API
class RetrofitClient @Inject constructor(private val api: APIService) : RemoteProvider {

    override suspend fun getPopularMovies(pageNumber : Int) : List<Movie> {

        return withContext(Dispatchers.IO) {
            // response holds the data fetched from the api
            val response = api.fetchMovies(page = pageNumber)
            // However we need a List of Movies, so we return the results if fetch was not null
            response.body()?.results ?: emptyList()
        }
    }

    override suspend fun postMovieRating(movieId: Int, userInput: UserRatingRequest): RatingResponse {
        return api.rateMovie(movieId, userInput)
    }
}