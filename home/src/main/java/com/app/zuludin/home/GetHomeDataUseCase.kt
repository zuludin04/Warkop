package com.app.zuludin.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.app.zuludin.data.WarkopRepository
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource

class GetHomeDataUseCase(private val repository: WarkopRepository) {
    suspend operator fun invoke(): LiveData<Resource<List<RestaurantsItem>>> {
        return Transformations.map(repository.loadFeaturedRestaurant()) { it }
    }
}