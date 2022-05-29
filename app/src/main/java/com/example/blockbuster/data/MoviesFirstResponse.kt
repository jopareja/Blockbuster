package com.example.blockbuster.data


data class MoviesFirstResponse(
    val page: Int,
    val results: List<Movie>
)