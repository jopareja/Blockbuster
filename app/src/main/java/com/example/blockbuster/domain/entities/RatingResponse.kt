package com.example.blockbuster.domain.entities

import com.squareup.moshi.Json

data class RatingResponse(
    @Json(name = "value") val movieRating: Number
)
