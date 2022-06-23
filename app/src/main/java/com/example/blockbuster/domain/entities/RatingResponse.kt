package com.example.blockbuster.domain.entities

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("status_message") val statusMessage: String
)
