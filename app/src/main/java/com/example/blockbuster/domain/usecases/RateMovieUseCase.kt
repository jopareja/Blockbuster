package com.example.blockbuster.domain.usecases

import com.example.blockbuster.data.repositories.MovieRepository
import com.example.blockbuster.domain.entities.RatingResponse
import com.example.blockbuster.domain.entities.UserRatingRequest
import javax.inject.Inject

class RateMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun rateMovie(userInput: UserRatingRequest) : RatingResponse {
        return repository.rateMovie(userInput)
    }
}