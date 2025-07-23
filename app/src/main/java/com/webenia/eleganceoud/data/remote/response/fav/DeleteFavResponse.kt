package com.webenia.eleganceoud.data.remote.response.fav

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteFavResponse(
	val message: String? = null
)

