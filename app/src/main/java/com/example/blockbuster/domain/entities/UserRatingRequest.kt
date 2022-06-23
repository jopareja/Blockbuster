package com.example.blockbuster.domain.entities

import com.google.gson.annotations.SerializedName

data class UserRatingRequest(
    @SerializedName("value") val value: Number
)
