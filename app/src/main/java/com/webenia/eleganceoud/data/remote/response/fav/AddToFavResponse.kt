package com.webenia.eleganceoud.data.remote.response.fav

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddToFavResponse(
	val message: String? = null
)

