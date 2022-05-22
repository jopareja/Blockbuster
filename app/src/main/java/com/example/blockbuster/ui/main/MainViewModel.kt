package com.example.blockbuster.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _requestStatus = MutableLiveData<String>()
    val requestStatus: LiveData<String> = _requestStatus

}