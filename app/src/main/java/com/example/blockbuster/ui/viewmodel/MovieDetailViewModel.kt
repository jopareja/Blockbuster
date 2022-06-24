package com.example.blockbuster.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blockbuster.domain.entities.UserRatingRequest
import com.example.blockbuster.domain.usecases.RateMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val useCase: RateMovieUseCase): ViewModel() {

    fun registerRating(movieId: Int, userInput: UserRatingRequest) {
        viewModelScope.launch {
            try {
            val rateApiResponse = useCase.rateMovie(movieId, userInput)
            Log.d("Jose", rateApiResponse.toString())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> Log.d("Jose", "IOException")
                    is HttpException -> when (throwable.code()) {
                        in 400..403 -> Log.d("Jose", "Http 401")
                        in 404..499 -> Log.d("Jose", "Http 404")
                        in 500..599 -> Log.d("Jose", "Http 500")
                        else -> Log.d("Jose", "Generic Error")
                    }
                    else -> Log.d("Jose", "A generic error")
                }
            }
        }
    }
}