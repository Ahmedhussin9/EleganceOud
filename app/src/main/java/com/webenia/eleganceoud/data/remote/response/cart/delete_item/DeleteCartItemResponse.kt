package com.webenia.eleganceoud.data.remote.response.cart.delete_item

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Json

@JsonClass(generateAdapter = true)
data class DeleteCartItemResponse(

	@Json(name="data")
	val data: Any? = null,

	@Json(name="message")
	val message: String? = null,

	@Json(name="status")
	val status: Boolean? = null
)
