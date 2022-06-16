package com.example.blockbuster.data.repositories

import com.example.blockbuster.domain.entities.Movie
import javax.inject.Inject

// The Repo that will decide to get data from a Remote or from a Local
class MovieRepository @Inject constructor(private val dataProvider: RemoteProvider) {


    suspend fun getPopularMovies(pageNumber: Int): List<Movie> {

        return dataProvider.getPopularMovies(pageNumber)
    }
}
