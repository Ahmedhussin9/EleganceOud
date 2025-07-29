package com.webenia.eleganceoud.domain.mapper

import com.webenia.eleganceoud.data.remote.response.cart.get_cart.DataItem
import com.webenia.eleganceoud.data.remote.response.cart.get_cart.GetCartResponse
import com.webenia.eleganceoud.domain.model.cart.CartModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun DataItem.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = product?.id ?: -1,
        name = product?.nameEn ?: "Product Name",
        price = convertedPrice ?: 0.0,
        imageUrl = images?.firstOrNull(),
        currencyCode = currencyCode ?: "AED",
        isAvailable = true,
        hasDiscount = discount != null,
        discount = product?.discount?.discountValue?.toDoubleOrNull(),
        priceAfterDiscount = product?.priceAfterDiscount?.toDoubleOrNull(),
        description = "",
    )
}

fun GetCartResponse.toCartModel(): CartModel {
    val firstItem = data?.original?.data?.firstOrNull()

    return CartModel(
        totalPrice = firstItem?.convertedTotal ?: 0.0,
        totalQuantity = firstItem?.quantity ?: 0,
        currencyCode = firstItem?.currencyCode ?: "AED",
        cartItems = data?.original?.data?.map {
            it?.toUiModel() ?: ProductUiModel(
                id = -1,
                name = "",
                price = 0.0,
                imageUrl = "",
                currencyCode = "",
                isAvailable = false,
                hasDiscount = false,
                discount = 0.0,
                priceAfterDiscount = 0.0,
                description = ""
            )
        } ?: emptyList()
    )
}
