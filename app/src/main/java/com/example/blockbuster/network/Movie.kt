package com.example.blockbuster.network

// Each movie response will come with below parameters:
data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    val poster: String,
)