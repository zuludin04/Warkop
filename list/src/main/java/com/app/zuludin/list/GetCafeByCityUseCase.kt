package com.app.zuludin.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.app.zuludin.data.WarkopRepository
import com.app.zuludin.data.model.RestaurantsItem
import com.app.zuludin.data.utils.Resource

class GetCafeByCityUseCase(private val repository: WarkopRepository) {
    suspend operator fun invoke(
        cityId: String?,
        entityType: String?,
        categoryId: String?
    ): LiveData<Resource<List<RestaurantsItem>>> {
        return Transformations.map(
            repository.loadCafeByCityId(
                cityId,
                entityType,
                categoryId
            )
        ) { it }
    }
}