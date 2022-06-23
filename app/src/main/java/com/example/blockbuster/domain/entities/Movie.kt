package com.example.blockbuster.domain.entities

import com.google.gson.annotations.SerializedName


// Each movie response will come with below parameters:
data class Movie(
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("poster_path") val imgSrcUrl: String?,
    @SerializedName("backdrop_path") val backdrop: String?,
    @SerializedName("vote_average") val rating: Float
)