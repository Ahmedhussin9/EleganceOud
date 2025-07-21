package com.webenia.eleganceoud.domain.mapper

import com.webenia.eleganceoud.data.remote.response.fav.GetFavoritesResponseItem
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun GetFavoritesResponseItem.toUiModel():ProductUiModel{
    return ProductUiModel(
        id = productId?:0,
        name = nameEn?:"",
        description = nameAr?:"",
        price = convertedPrice?:0.0,
        imageUrl = images?.firstOrNull(),
        currencyCode = currencyCode?:"",
        isAvailable = isAvailable==1,
        hasAmounts = false,
        hasDiscount = discount!=null,
        discount = discount?.value?.toDoubleOrNull(),
        priceAfterDiscount = priceAfterDiscount?.toDoubleOrNull(),
        isFavorite = true,
    )
}