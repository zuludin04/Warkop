package com.app.zuludin.data.source

import com.app.zuludin.data.source.api.ApiService

class WarkopRemoteSource(private val service: ApiService) {
    fun loadCafeByCityIdAsync(cityId: String?, entityType: String?, categoryId: String?) =
        service.getCafeResultByCityIdAsync(cityId, entityType, categoryId)

    fun loadDetailRestaurantAsync(resId: Int) = service.getRestaurantDetailAsync(resId)

    fun loadFeaturedRestaurantAsync() = service.getFeaturedRestaurantAsync("rating")

    fun loadSearchResultAsync(q: String) = service.getSearchResultAsync(q)

    fun loadRestaurantReview(resId: Int) = service.getRestaurantReviewsAsync(resId)
}