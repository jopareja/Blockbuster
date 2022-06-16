package com.example.blockbuster.data.repositories

import com.example.blockbuster.domain.entities.Movie

interface RemoteProvider {

    suspend fun getPopularMovies(pageNumber: Int) : List<Movie>
}