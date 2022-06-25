package com.example.blockbuster.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _rateStatus = MutableLiveData<RatingStatus>()
    val rateStatus: LiveData<RatingStatus> = _rateStatus

    fun registerRating(movieId: Int, userInput: UserRatingRequest) {
        viewModelScope.launch {
            try {
                val rateApiResponse = useCase.rateMovie(movieId, userInput)
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> _rateStatus.value = RatingStatus.IOException
                    is HttpException -> when (throwable.code()) {
                        in 400..403 -> _rateStatus.value = RatingStatus.HTTP401
                        in 404..499 -> _rateStatus.value = RatingStatus.HTTP404
                        else -> _rateStatus.value = RatingStatus.GenericError
                    }
                    else -> _rateStatus.value = RatingStatus.GenericError
                }
            }
        }
    }
}