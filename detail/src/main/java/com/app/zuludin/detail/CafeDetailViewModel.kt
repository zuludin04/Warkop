package com.app.zuludin.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.common.BaseViewModel
import com.app.zuludin.common.Event
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.detail.DetailCafeResponse
import com.app.zuludin.data.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CafeDetailViewModel(
    private val getDetailCafeUseCase: GetDetailCafeUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private var detailCafeSource: LiveData<Resource<DetailCafeResponse>> = MutableLiveData()

    private val detailCafeData = MediatorLiveData<DetailCafeResponse>()
    val cafe: LiveData<DetailCafeResponse> get() = detailCafeData

    private val loadingStatus = MutableLiveData<Resource.Status>()
    val loading: LiveData<Resource.Status> get() = loadingStatus


    fun loadCafeDetail(cafeId: String) = viewModelScope.launch(dispatchers.main) {
        detailCafeData.removeSource(detailCafeSource)

        withContext(dispatchers.io) {
            detailCafeSource = getDetailCafeUseCase(cafeId)
        }

        detailCafeData.addSource(detailCafeSource) {
            detailCafeData.value = it.data
            loadingStatus.value = it.status
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event(it.error?.message.toString())
        }
    }
}