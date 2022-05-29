package com.example.blockbuster.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blockbuster.data.Movie
import com.example.blockbuster.domain.GetPopularMoviesUseCase
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies


    // Get Popular Movies Use Case
    var GetPopularMoviesUseCase = GetPopularMoviesUseCase()

    fun updateMovies() {
        viewModelScope.launch {
            val result = GetPopularMoviesUseCase()

            if(!result.isNullOrEmpty()) {
                _movies.value = result
            }
        }
    }

}