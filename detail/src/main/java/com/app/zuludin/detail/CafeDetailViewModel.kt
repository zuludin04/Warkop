package com.app.zuludin.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.zuludin.common.base.BaseViewModel
import com.app.zuludin.common.Event
import com.app.zuludin.data.AppDispatchers
import com.app.zuludin.data.model.detail.DetailCafeResponse
import com.app.zuludin.data.model.review.UserReviewsItem
import com.app.zuludin.data.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CafeDetailViewModel(
    private val getDetailCafeUseCase: GetDetailCafeUseCase,
    private val getRestaurantReviewUseCase: GetRestaurantReviewUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private lateinit var idCafe: String

    private var detailCafeSource: LiveData<Resource<DetailCafeResponse>> = MutableLiveData()
    private var reviewSource: LiveData<Resource<List<UserReviewsItem>>> = MutableLiveData()

    private val detailCafeData = MediatorLiveData<DetailCafeResponse>()
    val cafe: LiveData<DetailCafeResponse> get() = detailCafeData

    private val reviewRestaurantData = MediatorLiveData<Resource<List<UserReviewsItem>>>()
    val reviews: LiveData<Resource<List<UserReviewsItem>>> get() = reviewRestaurantData

    private val loadingStatus = MutableLiveData<Resource.Status>()
    val loading: LiveData<Resource.Status> get() = loadingStatus

    private val errorLayout = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> get() = errorLayout

    fun loadCafeDetail(cafeId: String) {
        idCafe = cafeId
        getDetailData()
        getRestaurantReview()
    }

    fun refreshDetailLayout() = getDetailData()

    private fun getDetailData() = viewModelScope.launch(dispatchers.main) {
        detailCafeData.removeSource(detailCafeSource)

        withContext(dispatchers.io) {
            detailCafeSource = getDetailCafeUseCase(idCafe)
        }

        detailCafeData.addSource(detailCafeSource) {
            detailCafeData.value = it.data
            loadingStatus.value = it.status
            errorLayout.value = it.status == Resource.Status.ERROR
            if (it.status == Resource.Status.ERROR) _snackBarError.value =
                Event(it.error?.message.toString())
        }
    }

    private fun getRestaurantReview() = viewModelScope.launch(dispatchers.main) {
        reviewRestaurantData.removeSource(reviewSource)

        withContext(dispatchers.io) {
            reviewSource = getRestaurantReviewUseCase(idCafe)
        }

        reviewRestaurantData.addSource(reviewSource) {
            reviewRestaurantData.value = it
        }
    }
}