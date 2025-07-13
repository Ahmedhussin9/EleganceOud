package com.webenia.eleganceoud.domain.mapper

import Child
import Product
import com.elegance_oud.util.BASE_IMAGE_URL
import com.webenia.eleganceoud.domain.model.product.ProductAmountUiModel
import com.webenia.eleganceoud.domain.model.product.ProductDetailsUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun Product.toUiModel(): ProductDetailsUiModel {
    return ProductDetailsUiModel(
        id = id ?: -1,
        name = nameEn ?: "Product Name", // or `nameAr` if using Arabic
        description = descriptionEn ?: "Description",
        price = convertedPrice ?: 0.0,
        isAvailable = isAvailable == 1,
        mainImageUrl = images?.firstOrNull()?.let { BASE_IMAGE_URL + it.path } ?: "",
        discount = discount?.discountValue?:"",
        amounts = amounts?.map {
            ProductAmountUiModel(
                weight = it.weight ?: -1,
                price = it.price ?: "",
                unit = it.unit?.nameEn ?: "Unit"
            )
        } ?: emptyList(),
        currencyCode = currencyCode?:"AED",
        priceAfterDiscount = priceAfterDiscount?:0.0,
        relatedProducts = children?.map { it.toUiModel() } ?: emptyList(),
        imagesList = images?.map { BASE_IMAGE_URL + it.path } ?: emptyList())
}

fun Child.toUiModel(): ProductDetailsUiModel {
    return ProductDetailsUiModel(
        id = id ?: -1,
        name = nameEn ?: "Product Name", // or `nameAr` if using Arabic
        description = descriptionEn ?: "Description",
        price = convertedPrice ?: 0.0,
        isAvailable = isAvailable == 1,
        mainImageUrl = images?.firstOrNull()?.let { BASE_IMAGE_URL + it.path } ?: "",
        discount = discount?.discountValue?:"",
        amounts = emptyList(),
        relatedProducts = emptyList(),
        priceAfterDiscount = priceAfterDiscount?:0.0,
        imagesList = images?.map { BASE_IMAGE_URL + it.path } ?: emptyList(),
        currencyCode = currencyCode?:"AED"
    )
}
