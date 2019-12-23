package com.app.zuludin.data.model.detail

import com.google.gson.annotations.SerializedName

data class ZomatoEventsItem(

	@field:SerializedName("event")
	val event: Event? = null
)