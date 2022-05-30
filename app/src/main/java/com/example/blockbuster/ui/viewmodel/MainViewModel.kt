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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // Get Popular Movies Use Case
    var getPopularMoviesUseCase = GetPopularMoviesUseCase()

    fun updateMovies() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = getPopularMoviesUseCase()
                _isLoading.value = false
                _movies.value = result
            } catch (e:Exception) {
                _isLoading.value = true
                _movies.value = emptyList()
            }
        }
    }

}