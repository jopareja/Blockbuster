package com.example.blockbuster.network


data class MoviesFirstResponse(
    val page: Int,
    val results: List<Movie>
)