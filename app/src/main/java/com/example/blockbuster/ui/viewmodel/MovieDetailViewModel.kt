package com.example.blockbuster.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blockbuster.domain.entities.UserRatingRequest
import kotlinx.coroutines.launch

class MovieDetailViewModel: ViewModel() {

    fun registerRating(userInput: UserRatingRequest) {
        viewModelScope.launch {
            Log.d("Jose", userInput.toString())
        }
    }
}