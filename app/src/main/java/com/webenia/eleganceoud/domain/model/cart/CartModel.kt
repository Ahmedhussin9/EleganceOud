package com.webenia.eleganceoud.domain.model.cart

import com.webenia.eleganceoud.domain.model.product.ProductUiModel

data class CartModel (
    val totalPrice:Double,
    val totalQuantity:Int,
    val currencyCode:String,
    val cartItems:List<CartItemModel>?
)