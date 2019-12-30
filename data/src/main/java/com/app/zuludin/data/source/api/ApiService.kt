package com.app.zuludin.data.source.api

import com.app.zuludin.data.model.SearchCafeResponse
import com.app.zuludin.data.model.detail.DetailCafeResponse
import com.app.zuludin.data.model.review.ReviewResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    fun getCafeResultByCityIdAsync(
        @Query("entity_id") entityId: String?,
        @Query("entity_type") entityType: String?,
        @Query("category") categoryId: String?
    ): Deferred<SearchCafeResponse>

    @GET("restaurant")
    fun getRestaurantDetailAsync(@Query("res_id") resId: Int): Deferred<DetailCafeResponse>

    @GET("search")
    fun getFeaturedRestaurantAsync(@Query("sort") sort: String): Deferred<SearchCafeResponse>

    @GET("search")
    fun getSearchResultAsync(@Query("q") query: String): Deferred<SearchCafeResponse>

    @GET("reviews")
    fun getRestaurantReviewsAsync(@Query("res_id") resId: Int): Deferred<ReviewResponse>
}