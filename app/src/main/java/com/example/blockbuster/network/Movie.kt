package com.example.blockbuster.network

import com.squareup.moshi.Json


// Each movie response will come with below parameters:
data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    @Json(name = "poster_path") val imgSrcUrl: String,
)