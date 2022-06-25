package com.example.blockbuster.ui.viewmodel

sealed class RatingStatus {
    object IOException: RatingStatus()
    object HTTP401: RatingStatus()
    object HTTP404: RatingStatus()
    object GenericError: RatingStatus()
}
