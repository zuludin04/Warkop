package com.app.zuludin.data.source

import com.app.zuludin.data.source.api.ApiService

class WarkopRemoteSource(private val service: ApiService) {
    fun loadCafeByCityIdAsync(cityId: String?, entityType: String?, categoryId: String?) =
        service.getCafeResultByCityIdAsync(cityId, entityType, categoryId)

    fun loadDetailRestaurantAsync(resId: Int) = service.getRestaurantDetailAsync(resId)

    fun loadFeaturedRestaurantAsycn() = service.getFeaturedRestaurantAsync("rating")
}