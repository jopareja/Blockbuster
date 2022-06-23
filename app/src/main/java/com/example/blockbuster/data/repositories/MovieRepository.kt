package com.example.blockbuster.data.repositories

import com.example.blockbuster.domain.entities.Movie
import com.example.blockbuster.domain.entities.RatingResponse
import com.example.blockbuster.domain.entities.UserRatingRequest
import javax.inject.Inject

// The Repo that will decide to get data from a Remote or from a Local
class MovieRepository @Inject constructor(private val dataProvider: RemoteProvider) {


    suspend fun getPopularMovies(pageNumber: Int): List<Movie> {
        return dataProvider.getPopularMovies(pageNumber)
    }

    suspend fun rateMovie(movieId: Int, userInput: UserRatingRequest) : RatingResponse? {
        return dataProvider.postMovieRating(movieId, userInput)
    }
}
