package com.app.zuludin.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {
    protected val _snackBarError = MutableLiveData<Event<String>>()
    val errorMessage: LiveData<Event<String>> get() = _snackBarError
}