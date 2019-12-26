package com.app.zuludin.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.zuludin.common.Event

abstract class BaseViewModel : ViewModel() {
    protected val _snackBarError = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> get() = _snackBarError
}