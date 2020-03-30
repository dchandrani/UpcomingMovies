package com.app.upcomingmovies.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
}
