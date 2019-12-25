package com.app.zuludin.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.common.BaseViewModel
import com.app.zuludin.common.Event
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CafeListViewModel(
    private val getCafeByCityUseCase: GetCafeByCityUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private lateinit var city: String
    private lateinit var entity: String
    private lateinit var category: String

    private val cafeListData = MediatorLiveData<Resource<List<RestaurantsItem>>>()
    val cafes: LiveData<Resource<List<RestaurantsItem>>> get() = cafeListData
    private var cafeSource: LiveData<Resource<List<RestaurantsItem>>> = MutableLiveData()

    private val statusLoading = MutableLiveData<Resource.Status>()
    val loading: LiveData<Resource.Status> get() = statusLoading

    private val errorLayout = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = errorLayout

    fun loadCafes(cityId: String?, entityType: String?, categoryId: String?) {
        city = cityId.toString()
        entity = entityType.toString()
        category = categoryId.toString()
        getRestaurantList()
    }

    fun refreshLayout() = getRestaurantList()

    private fun getRestaurantList() = viewModelScope.launch(dispatchers.main) {
        cafeListData.removeSource(cafeSource)

        withContext(dispatchers.io) {
            cafeSource = getCafeByCityUseCase(city, entity, category)
        }

        cafeListData.addSource(cafeSource) {
            cafeListData.value = it
            statusLoading.value = it.status
            errorLayout.value = it.status == Resource.Status.ERROR
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event(it.error?.message.toString())
        }
    }
}