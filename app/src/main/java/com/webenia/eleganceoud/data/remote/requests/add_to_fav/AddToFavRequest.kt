package com.webenia.eleganceoud.data.remote.requests.add_to_fav

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddToFavRequest(
    val product_id: Int,
)
