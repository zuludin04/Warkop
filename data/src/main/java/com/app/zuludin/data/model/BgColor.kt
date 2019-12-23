package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class BgColor(

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("tint")
	val tint: String? = null
)