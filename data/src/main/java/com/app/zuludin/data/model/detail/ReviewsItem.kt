package com.app.zuludin.data.model.detail

import com.google.gson.annotations.SerializedName

data class ReviewsItem(

	@field:SerializedName("review")
	val review: List<Any>? = null
)