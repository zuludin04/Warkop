package com.app.zuludin.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.app.zuludin.data.WarkopRepository
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource

class GetSearchRestaurantUseCase(private val repository: WarkopRepository) {
    suspend operator fun invoke(query: String): LiveData<Resource<List<RestaurantsItem>>> {
        return Transformations.map(repository.loadSearchRestaurant(query)) { it }
    }
}