package com.example.blockbuster.domain.entities

import com.squareup.moshi.Json

data class RatingResponse(
    @Json(name = "status_code") val statusCode: Int,
    @Json(name = "status_message") val statusMessage: String
)
