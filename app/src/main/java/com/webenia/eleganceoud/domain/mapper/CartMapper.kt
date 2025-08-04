package com.webenia.eleganceoud.domain.mapper

import com.webenia.eleganceoud.data.remote.response.cart.get_cart.DataItem
import com.webenia.eleganceoud.data.remote.response.cart.get_cart.GetCartResponse
import com.webenia.eleganceoud.domain.model.cart.CartItemModel
import com.webenia.eleganceoud.domain.model.cart.CartModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun DataItem.toUiModel(): CartItemModel {
    return CartItemModel(
        id = product?.id ?: -1,
        name = product?.nameEn ?: "Product Name",
        price =product?.convertedPrice?: 0.0,
        priceAfterDiscount = product?.convertedTotal?:0.0,
        imageUrl = images?.firstOrNull(),
        currencyCode = currencyCode ?: "AED",
        hasDiscount = discount != null,
        discount = product?.discount?.discountValue?.toDoubleOrNull(),
        description = "",
        countInCart = quantity ?: 1
    )
}

fun GetCartResponse.toCartModel(): CartModel {
    val firstItem = data?.original?.data?.firstOrNull()

    return CartModel(
        totalPrice = firstItem?.convertedTotal ?: 0.0,
        totalQuantity = firstItem?.quantity ?: 0,
        currencyCode = firstItem?.currencyCode ?: "AED",
        cartItems = data?.original?.data?.map {
            it?.toUiModel() ?: CartItemModel(
                id = -1,
                name = "",
                price = 0.0,
                imageUrl = "",
                currencyCode = "",
                hasDiscount = false,
                discount = 0.0,
                priceAfterDiscount = 0.0,
                description = "",
                countInCart = 1
            )
        } ?: emptyList()
    )
}
