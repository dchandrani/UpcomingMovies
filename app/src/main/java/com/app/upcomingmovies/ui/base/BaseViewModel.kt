package com.app.upcomingmovies.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val _isLoading = MutableLiveData<Boolean>()
    protected val _error = MutableLiveData<String>()

    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val error: LiveData<String>
        get() = _error
}