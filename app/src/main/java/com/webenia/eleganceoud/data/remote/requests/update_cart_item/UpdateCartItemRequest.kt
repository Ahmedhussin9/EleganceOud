package com.webenia.eleganceoud.data.remote.requests.update_cart_item

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateCartItemRequest(
    val quantity: Int
)