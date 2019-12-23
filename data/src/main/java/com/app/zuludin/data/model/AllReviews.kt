package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class AllReviews(
	@field:SerializedName("reviews")
	val reviews: List<ReviewsItem>? = null
)