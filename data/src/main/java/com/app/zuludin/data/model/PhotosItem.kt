package com.app.zuludin.data.model

import com.google.gson.annotations.SerializedName

data class PhotosItem(

	@field:SerializedName("photo")
	val photo: Photo? = null
)