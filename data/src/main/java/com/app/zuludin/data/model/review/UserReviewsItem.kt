package com.app.zuludin.data.model.review

import com.google.gson.annotations.SerializedName

data class UserReviewsItem(

	@field:SerializedName("review")
	val review: Review? = null
)