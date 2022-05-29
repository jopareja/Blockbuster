package com.example.blockbuster.ui.viewmodel

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
        updateMovies(noPage = 1)
    }


    // Update List of Movies with Retrofit's getPopularMovies Information
    fun updateMovies(noPage : Int) {
        //Launching Coroutine
        viewModelScope.launch {
            val popularMovieRequest = Api.retrofitService.getPopularMovies(noPage)
            try {
                val popularMovieResponse = popularMovieRequest.body()
                val popularMovies = popularMovieResponse?.results
                _movies.value = popularMovies!!
                _requestStatus.value = "Success"
            } catch (e:Exception) {
                _requestStatus.value = "Failure"
            }
        }
    }
}