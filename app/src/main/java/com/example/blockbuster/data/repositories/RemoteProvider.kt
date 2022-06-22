package com.example.blockbuster.data.repositories

import com.example.blockbuster.domain.entities.Movie
import com.example.blockbuster.domain.entities.RatingResponse
import com.example.blockbuster.domain.entities.UserRatingRequest

interface RemoteProvider {

    suspend fun getPopularMovies(pageNumber: Int) : List<Movie>

    suspend fun postMovieRating(userInput: UserRatingRequest) : RatingResponse
}