package com.example.blockbuster.domain

import com.example.blockbuster.data.Movie
import com.example.blockbuster.data.MovieRepository

//This class needs to recover from data, in this case something related to the MovieRepository.
// There could be other use cases that need data related to a different repository, like a FoodRepo.
class GetPopularMoviesUseCase {

    //In this use case we need data related to Movies, so we call the MovieRepository
    private val repository = MovieRepository()

    suspend operator fun invoke() : List<Movie> = repository.getPopularMovies(1)
}