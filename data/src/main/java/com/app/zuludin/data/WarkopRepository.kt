package com.app.zuludin.data

import androidx.lifecycle.LiveData
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.model.SearchCafeResponse
import com.app.zuludin.data.model.detail.DetailCafeResponse
import com.app.zuludin.data.model.review.ReviewResponse
import com.app.zuludin.data.model.review.UserReviewsItem
import com.app.zuludin.data.source.WarkopRemoteSource
import com.app.zuludin.data.utils.NetworkBoundResource
import com.app.zuludin.data.utils.Resource
import kotlinx.coroutines.Deferred

interface WarkopRepository {
    suspend fun loadCafeByCityId(
        cityId: String?,
        entityType: String?,
        categoryId: String?
    ): LiveData<Resource<List<RestaurantsItem>>>

    suspend fun loadCafeDetail(cafeId: String): LiveData<Resource<DetailCafeResponse>>
    suspend fun loadFeaturedRestaurant(): LiveData<Resource<List<RestaurantsItem>>>
    suspend fun loadSearchRestaurant(query: String): LiveData<Resource<List<RestaurantsItem>>>
    suspend fun loadRestaurantReview(resId: String): LiveData<Resource<List<UserReviewsItem>>>
}

class WarkopRepositoryImpl(
    private val remote: WarkopRemoteSource
) : WarkopRepository {
    override suspend fun loadCafeByCityId(
        cityId: String?,
        entityType: String?,
        categoryId: String?
    ): LiveData<Resource<List<RestaurantsItem>>> {
        return object : NetworkBoundResource<List<RestaurantsItem>, SearchCafeResponse>() {
            override fun processResponse(response: SearchCafeResponse?): List<RestaurantsItem>? =
                response?.restaurants

            override fun shouldFetch(data: List<RestaurantsItem>?): Boolean =
                data == null || data.isEmpty()

            override fun createCallAsync(): Deferred<SearchCafeResponse> =
                remote.loadCafeByCityIdAsync(cityId, entityType, categoryId)

        }.build().asLiveData()
    }

    override suspend fun loadCafeDetail(cafeId: String): LiveData<Resource<DetailCafeResponse>> {
        return object : NetworkBoundResource<DetailCafeResponse, DetailCafeResponse>() {
            override fun processResponse(response: DetailCafeResponse?): DetailCafeResponse? =
                response

            override fun shouldFetch(data: DetailCafeResponse?): Boolean = data == null

            override fun createCallAsync(): Deferred<DetailCafeResponse> =
                remote.loadDetailRestaurantAsync(cafeId.toInt())

        }.build().asLiveData()
    }

    override suspend fun loadFeaturedRestaurant(): LiveData<Resource<List<RestaurantsItem>>> {
        return object : NetworkBoundResource<List<RestaurantsItem>, SearchCafeResponse>() {
            override fun processResponse(response: SearchCafeResponse?): List<RestaurantsItem>? =
                response?.restaurants

            override fun shouldFetch(data: List<RestaurantsItem>?): Boolean =
                data == null || data.isEmpty()

            override fun createCallAsync(): Deferred<SearchCafeResponse> =
                remote.loadFeaturedRestaurantAsync()

        }.build().asLiveData()
    }

    override suspend fun loadSearchRestaurant(query: String): LiveData<Resource<List<RestaurantsItem>>> {
        return object : NetworkBoundResource<List<RestaurantsItem>, SearchCafeResponse>() {
            override fun processResponse(response: SearchCafeResponse?): List<RestaurantsItem>? =
                response?.restaurants

            override fun shouldFetch(data: List<RestaurantsItem>?): Boolean =
                data == null || data.isEmpty()

            override fun createCallAsync(): Deferred<SearchCafeResponse> =
                remote.loadSearchResultAsync(query)

        }.build().asLiveData()
    }

    override suspend fun loadRestaurantReview(resId: String): LiveData<Resource<List<UserReviewsItem>>> {
        return object : NetworkBoundResource<List<UserReviewsItem>, ReviewResponse>() {
            override fun processResponse(response: ReviewResponse?): List<UserReviewsItem>? =
                response?.userReviews

            override fun shouldFetch(data: List<UserReviewsItem>?): Boolean =
                data == null || data.isEmpty()

            override fun createCallAsync(): Deferred<ReviewResponse> =
                remote.loadRestaurantReview(resId.toInt())

        }.build().asLiveData()
    }
}