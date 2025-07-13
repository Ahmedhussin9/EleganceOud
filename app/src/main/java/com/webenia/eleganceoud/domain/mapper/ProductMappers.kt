package com.webenia.eleganceoud.domain.mapper

import com.webenia.eleganceoud.data.remote.response.home.our_products.Product
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun Product.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id?:-1,
        name = nameEn?: "Product Name",
        description = descriptionEn?:"Description",
        price = convertedPrice?:0.0,
        imageUrl = images?.firstOrNull()?.path,
        currencyCode = currencyCode?:"AED",
        isAvailable = isAvailable == 1,
        parentName = parent?.nameEn,
        hasAmounts = amounts?.isNotEmpty()?:false,
        hasDiscount = discount != null,
        discount = discount?.discountValue?.toDoubleOrNull(),
        priceAfterDiscount = priceAfterDiscount?:0.0,
    )
}

fun com.webenia.eleganceoud.data.remote.response.home.best_sellings.Product.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id?:-1,
        name = nameEn?: "Product Name",
        description = descriptionEn?:"Description",
        price = convertedPrice?:0.0,
        imageUrl = images?.firstOrNull()?.path,
        currencyCode = currencyCode?:"AED",
        isAvailable = isAvailable == 1,
        parentName = parent?.nameEn,
        hasAmounts = amounts?.isNotEmpty()?:false,
        hasDiscount = discount != null,
        discount = discount?.discountValue?.toDoubleOrNull(),
        priceAfterDiscount = priceAfterDiscount?:0.0,
    )
}

fun com.webenia.eleganceoud.data.remote.response.home.latest_products.Product.toUiModel(): ProductUiModel =
    ProductUiModel(
        id = id?:-1,
        name = nameEn?: "Product Name",
        description = descriptionEn?:"Description",
        price = convertedPrice?:0.0,
        imageUrl = images?.firstOrNull()?.path,
        currencyCode = currencyCode?:"AED",
        isAvailable = isAvailable == 1,
        parentName = parent?.nameEn,
        hasAmounts = amounts?.isNotEmpty()?:false,
        hasDiscount = discount != null,
        discount = discount?.discountValue?.toDoubleOrNull(),
        priceAfterDiscount = priceAfterDiscount?:0.0,
        )