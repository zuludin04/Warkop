package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class Photo(

	@field:SerializedName("thumb_url")
	val thumbUrl: String? = null,

	@field:SerializedName("friendly_time")
	val friendlyTime: String? = null,

	@field:SerializedName("res_id")
	val resId: Int? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("caption")
	val caption: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
)