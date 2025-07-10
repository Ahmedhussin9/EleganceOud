package com.webenia.eleganceoud.domain.mapper

import Product
import com.elegance_oud.util.BASE_IMAGE_URL
import com.webenia.eleganceoud.domain.model.product.ProductAmountUiModel
import com.webenia.eleganceoud.domain.model.product.ProductDetailsUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun Product.toUiModel(): ProductDetailsUiModel {
    return ProductDetailsUiModel(
        id = id,
        name = nameEn, // or `nameAr` if using Arabic
        description = descriptionEn,
        price = "$price $currencyCode",
        isAvailable = isAvailable == 1,
        mainImageUrl = images?.firstOrNull()?.let { BASE_IMAGE_URL + it.path } ?: "",
        discount = discount?.let { "${it.discountValue}% OFF" },
        amounts = amounts.map {
            ProductAmountUiModel(
                weight = it.weight,
                price = it.price,
                unit = it.unit.nameEn
            )
        },
        relatedProducts = children.map { it.toUiModel() },
        imagesList = images?.map { BASE_IMAGE_URL + it.path } ?: emptyList())
}
