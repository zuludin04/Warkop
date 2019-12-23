package com.app.zuludin.data.model.detail

import com.google.gson.annotations.SerializedName

data class RatingObj(

	@field:SerializedName("bg_color")
	val bgColor: BgColor? = null,

	@field:SerializedName("title")
	val title: Title? = null
)