package com.webenia.eleganceoud.domain.mapper

import com.webenia.eleganceoud.data.remote.response.category_product.ProductsItem
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun ProductsItem.toUiModel():ProductUiModel{
    return ProductUiModel(
        id = id ?: -1,
        name = nameEn ?: "Product Name", // or `nameAr` if using Arabic
        description = descriptionEn ?: "Description",
        price = if (discount == null) convertedPrice ?: 0.0 else discountedPrice ?: 0.0,
        isAvailable = isAvailable == 1,
        currencyCode = currencyCode ?: "AED",
        imageUrl = images?.firstOrNull()?.path ?: "",
        isFavorite = isFavorite

    )
}