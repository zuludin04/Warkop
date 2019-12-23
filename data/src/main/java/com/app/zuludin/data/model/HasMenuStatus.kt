package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class HasMenuStatus(

	@field:SerializedName("delivery")
	val delivery: Int? = null,

	@field:SerializedName("takeaway")
	val takeaway: Int? = null
)