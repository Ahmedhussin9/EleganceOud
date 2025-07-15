package com.webenia.eleganceoud.domain.mapper

import Child
import ParentProduct
import Product
import com.elegance_oud.util.BASE_IMAGE_URL
import com.webenia.eleganceoud.domain.model.product.ProductAmountUiModel
import com.webenia.eleganceoud.domain.model.product.ProductDetailsUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun Product.toUiModel(): ProductDetailsUiModel {
    return ProductDetailsUiModel(
        id = id ?: -1,
        name = nameEn ?: "Product Name",
        description = descriptionEn ?: "Description",
        price = (if (amounts.isNullOrEmpty()) convertedPrice else amounts[0].product?.convertedPrice)
            ?: 0.0,
        isAvailable = isAvailable == 1,
        mainImageUrl = images?.firstOrNull()?.let { BASE_IMAGE_URL + it.path } ?: "",
        discount = discount?.discountValue ?: "",
        amounts = amounts?.map {
            ProductAmountUiModel(
                weight = it.weight ?: -1,
                price = it.product?.convertedPrice.toString(),
                priceAfter = it.discountedPrice,
                unit = it.unit?.nameEn ?: "Unit"
            )
        } ?: emptyList(),
        hasAmount = !amounts.isNullOrEmpty(),
        currencyCode = currencyCode ?: "AED",
        priceAfterDiscount = (if (amounts.isNullOrEmpty()) priceAfterDiscount else amounts[0].product?.priceAfterDiscount)
            ?: 0.0,
        relatedProducts = if (!children.isNullOrEmpty()) children.map { it.toUiModel() } else if (parent != null) listOf(
            parent.toUiModel()
        ) else
            emptyList(),
        parentProduct = parent,
        imagesList = images?.map { BASE_IMAGE_URL + it.path } ?: emptyList())
}

fun Child.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id ?: -1,
        name = nameEn ?: "Product Name", // or `nameAr` if using Arabic
        description = descriptionEn ?: "Description",
        price = convertedPrice ?: 0.0,
        isAvailable = isAvailable == 1,
        discount = discount?.discountValue?.toDoubleOrNull() ?: 0.0,
        priceAfterDiscount = priceAfterDiscount ?: 0.0,
        currencyCode = currencyCode ?: "AED",
        imageUrl = images?.firstOrNull()?.let { BASE_IMAGE_URL + it.path } ?: "",
    )
}

fun ParentProduct.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id ?: -1,
        name = nameEn ?: "Product Name", // or `nameAr` if using Arabic
        description = descriptionEn ?: "Description",
        price = convertedPrice ?: 0.0,
        isAvailable = isAvailable == 1,
        currencyCode = currencyCode ?: "AED",
        imageUrl = image,
    )


}