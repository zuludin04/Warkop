package com.app.zuludin.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.common.Event
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getHomeDataUseCase: GetHomeDataUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val featuredRestaurant = MediatorLiveData<Resource<List<RestaurantsItem>>>()
    val restaurant: LiveData<Resource<List<RestaurantsItem>>> get() = featuredRestaurant
    private var restaurantSource: LiveData<Resource<List<RestaurantsItem>>> = MutableLiveData()

    private val statusLoading = MutableLiveData<Resource.Status>()
    val loading: LiveData<Resource.Status> get() = statusLoading

    private val errorLayout = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = errorLayout

    init {
        loadHomeFeatured()
    }

    fun refreshLayout() = loadHomeFeatured()

    private fun loadHomeFeatured() = viewModelScope.launch(dispatchers.main) {
        featuredRestaurant.removeSource(restaurantSource)

        withContext(dispatchers.io) {
            restaurantSource = getHomeDataUseCase()
        }

        featuredRestaurant.addSource(restaurantSource) {
            featuredRestaurant.value = it
            statusLoading.value = it.status
            errorLayout.value = it.status == Resource.Status.ERROR
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event(it.error?.message.toString())
        }
    }
}