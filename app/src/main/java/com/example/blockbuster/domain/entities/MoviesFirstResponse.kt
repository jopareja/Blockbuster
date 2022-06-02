package com.example.blockbuster.domain.entities

import com.example.blockbuster.domain.entities.Movie


data class MoviesFirstResponse(
    val page: Int,
    val results: List<Movie>
)