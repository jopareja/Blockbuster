package com.example.blockbuster.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blockbuster.domain.entities.Movie
import com.example.blockbuster.domain.usecases.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status


    fun updateMovies() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                _movies.value = getPopularMoviesUseCase.invoke()
                _status.value = ApiStatus.DONE
            } catch (e:Exception) {
                _status.value = ApiStatus.ERROR
                _movies.value = emptyList()
            }
        }
    }

    fun filterMovies(someText: String?) {
        _movies.value = _movies.value?.filter { it.title.uppercase().contains(someText!!.uppercase()) }
    }

}