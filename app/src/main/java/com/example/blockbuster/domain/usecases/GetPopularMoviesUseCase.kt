package com.example.blockbuster.domain.usecases

import com.example.blockbuster.domain.entities.Movie
import com.example.blockbuster.data.repositories.MovieRepository
import javax.inject.Inject

//This class needs to recover from data, in this case something related to the MovieRepository.
// There could be other use cases that need data related to a different repository, like a FoodRepo.
class GetPopularMoviesUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend fun invoke() : List<Movie> = repository.getPopularMovies(1)
}