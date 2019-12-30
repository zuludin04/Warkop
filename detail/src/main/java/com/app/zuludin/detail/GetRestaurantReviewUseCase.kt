package com.app.zuludin.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.app.zuludin.data.WarkopRepository
import com.app.zuludin.data.model.review.UserReviewsItem
import com.app.zuludin.data.utils.Resource

class GetRestaurantReviewUseCase(private val repository: WarkopRepository) {
    suspend operator fun invoke(resId: String): LiveData<Resource<List<UserReviewsItem>>> {
        return Transformations.map(repository.loadRestaurantReview(resId)) { it }
    }
}