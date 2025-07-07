package com.webenia.eleganceoud.domain.mapper

import com.webenia.eleganceoud.data.remote.response.home.our_products.Product
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun Product.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id,
        name = nameAr,
        description = descriptionAr,
        price = price,
        imageUrl = images.firstOrNull()?.path,
        currencyCode = currency.code,
        isAvailable = isAvailable == 1,
        parentName = parent?.nameAr,
        hasAmounts = amounts.isNotEmpty()
    )
}

fun com.webenia.eleganceoud.data.remote.response.home.best_sellings.Product.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id,
        name = name_en,
        description = description_en ?: "",
        price = price,
        imageUrl = images?.firstOrNull()?.path,
        currencyCode = currency.code,
        isAvailable = is_available == 1,
        parentName = null,
        hasAmounts = false
    )
}