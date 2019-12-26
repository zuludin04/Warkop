package com.app.zuludin.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.common.Event
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchRestaurantViewModel(
    private val getSearchRestaurantUseCase: GetSearchRestaurantUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val restaurantResult = MediatorLiveData<Resource<List<RestaurantsItem>>>()
    val restaurants: LiveData<Resource<List<RestaurantsItem>>> get() = restaurantResult
    private var resultSource: LiveData<Resource<List<RestaurantsItem>>> = MutableLiveData()

    private val loadingProgress = MutableLiveData<Resource.Status>()
    val loading: LiveData<Resource.Status> get() = loadingProgress

    fun searchRestaurant(query: String) = viewModelScope.launch(dispatchers.main) {
        restaurantResult.removeSource(resultSource)

        withContext(dispatchers.io) {
            resultSource = getSearchRestaurantUseCase(query)
        }

        restaurantResult.addSource(resultSource) {
            restaurantResult.value = it
            loadingProgress.value = it.status
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event(it.error?.message.toString())
        }
    }
}