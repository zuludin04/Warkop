package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class TypesItem(

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)