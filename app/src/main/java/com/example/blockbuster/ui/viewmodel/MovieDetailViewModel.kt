package com.example.blockbuster.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blockbuster.domain.entities.UserRatingRequest
import com.example.blockbuster.domain.usecases.RateMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val useCase: RateMovieUseCase): ViewModel() {

    fun registerRating(userInput: UserRatingRequest) {
        viewModelScope.launch {
            val rateApiResponse = useCase.rateMovie(userInput)
        }
    }
}