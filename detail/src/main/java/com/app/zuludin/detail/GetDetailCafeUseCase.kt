package com.app.zuludin.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.app.zuludin.data.WarkopRepository
import com.app.zuludin.data.model.detail.DetailCafeResponse
import com.app.zuludin.data.utils.Resource

class GetDetailCafeUseCase(private val repository: WarkopRepository) {
    suspend operator fun invoke(cafeId: String): LiveData<Resource<DetailCafeResponse>> {
        return Transformations.map(repository.loadCafeDetail(cafeId)) { it }
    }
}