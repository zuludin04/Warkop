package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class RestaurantsItem(

	@field:SerializedName("restaurant")
	val restaurant: Restaurant? = null
)