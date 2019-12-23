package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class SearchCafeResponse(

	@field:SerializedName("results_found")
	val resultsFound: Int? = null,

	@field:SerializedName("results_shown")
	val resultsShown: Int? = null,

	@field:SerializedName("restaurants")
	val restaurants: List<RestaurantsItem>? = null,

	@field:SerializedName("results_start")
	val resultsStart: Int? = null
)