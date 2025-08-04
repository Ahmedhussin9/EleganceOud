package com.webenia.eleganceoud.domain.model.cart

data class CartItemModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String?,
    val currencyCode: String,
    val hasAmounts: Boolean = false,
    val hasDiscount: Boolean = false,
    val discount: Double? = null,
    val priceAfterDiscount: Double? = null,
    val countInCart : Int = 0,
    val totalPrice : Double = 0.0
)
