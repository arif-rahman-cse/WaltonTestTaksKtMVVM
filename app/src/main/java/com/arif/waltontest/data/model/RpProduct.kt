package com.arif.waltontest.data.model

import com.google.gson.annotations.SerializedName

data class RpProduct(

	@field:SerializedName("modelName")
	val modelName: String? = null,

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("updatedOn")
	val updatedOn: String? = null,

	@field:SerializedName("createdOn")
	val createdOn: String? = null,

	@field:SerializedName("lUser")
	val lUser: String? = null,

	@field:SerializedName("productType")
	val productType: String? = null,

	@field:SerializedName("isStock")
	val isStock: Boolean? = null
)
