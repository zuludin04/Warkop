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

    private val cafeListData = MediatorLiveData<Resource<List<RestaurantsItem>>>()
    val cafes: LiveData<Resource<List<RestaurantsItem>>> get() = cafeListData
    private var cafeSource: LiveData<Resource<List<RestaurantsItem>>> = MutableLiveData()

    private val statusLoading = MutableLiveData<Resource.Status>()
    val loading: LiveData<Resource.Status> get() = statusLoading

    fun loadCafes(cityId: String?, entityType: String?, categoryId: String?) = viewModelScope.launch(dispatchers.main) {
        cafeListData.removeSource(cafeSource)

        withContext(dispatchers.io) {
            cafeSource = getCafeByCityUseCase(cityId, entityType, categoryId)
        }

        cafeListData.addSource(cafeSource) {
            cafeListData.value = it
            statusLoading.value = it.status
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event(it.error?.message.toString())
        }
    }
}