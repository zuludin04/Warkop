package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class Ray(

	@field:SerializedName("has_menu_status")
	val hasMenuStatus: HasMenuStatus? = null,

	@field:SerializedName("res_id")
	val resId: Int? = null
)