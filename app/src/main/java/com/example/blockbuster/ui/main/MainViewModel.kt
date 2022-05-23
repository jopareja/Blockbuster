package com.example.blockbuster.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blockbuster.network.Api
import com.example.blockbuster.network.Movie
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _requestStatus = MutableLiveData<String>()
    val requestStatus: LiveData<String> = _requestStatus

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    init {
        updateMovies()
    }


    // Update List of Movies with Retrofit's getPopularMovies Information
    private fun updateMovies() {
        //Launching Coroutine
        viewModelScope.launch {
            try {
                val listResult = Api.retrofitService.getPopularMovies()
                _requestStatus.value = "Success: ${listResult.size} Movies retrieved"
            } catch (e:Exception) {
                _requestStatus.value = "Failure: ${e.message}"
            }
        }
    }
}