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

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    // Get Popular Movies Use Case
    var getPopularMoviesUseCase = GetPopularMoviesUseCase()

    fun updateMovies() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                val result = getPopularMoviesUseCase()
                _movies.value = result
                _status.value = ApiStatus.DONE
            } catch (e:Exception) {
                _status.value = ApiStatus.ERROR
                _movies.value = emptyList()
            }
        }
    }

    fun filterMovies(someText: String?) {
        _movies.value = _movies.value?.filter { it.title.contains(someText!!) }
    }

}