package com.webenia.eleganceoud.data.remote.requests.add_to_cart

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddToCartRequest (
    val product_id:Int,
    val quantity:Int
)