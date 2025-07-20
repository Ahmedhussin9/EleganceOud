package com.webenia.eleganceoud.domain.mapper

import Child
import Parent
import ProductResponse
import com.elegance_oud.util.BASE_IMAGE_URL
import com.webenia.eleganceoud.domain.model.product.ProductAmountUiModel
import com.webenia.eleganceoud.domain.model.product.ProductDetailsUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

fun ProductResponse.toUiModel(): ProductDetailsUiModel {

    return ProductDetailsUiModel(
        id = data?.id ?: -1,
        name = data?.nameEn ?: "Product Name",
        description = data?.descriptionEn ?: "Description",
        price = if (data?.amounts.isNullOrEmpty()) data?.convertedPrice else data?.amounts?.firstOrNull()?.convertedPrice,
        isAvailable = data?.isAvailable == 1,
        mainImageUrl = data?.images?.firstOrNull()?.let { BASE_IMAGE_URL + it.path } ?: "",
        discountValue = data?.discount?.discountValue,
        amounts = data?.amounts?.map {
            val hasDiscount = data.discount != null
            ProductAmountUiModel(
                weight = it?.weight ?: 0,
                price = if (hasDiscount) it?.convertedPrice.toString() else it?.convertedPrice.toString(),
                priceAfter = if (hasDiscount) it?.discountedPrice.toString() else null,
                unit = it?.unit?.nameEn ?: "kg",
                currencyCode = it?.currencyCode ?: "AED"
            )
        } ?: emptyList(),
        hasAmount = data?.amounts.isNullOrEmpty(),
        currencyCode = data?.currencyCode ?: "AED",
        priceAfterDiscount = if (data?.amounts.isNullOrEmpty()) data?.discountedPrice else data?.amounts?.firstOrNull()?.discountedPrice,
        relatedProducts = data?.children?.map {
            it?.toUiModel() ?: ProductUiModel(
                id = it?.id ?: -1,
                name = it?.nameEn ?: "Product Name",
                description = it?.descriptionEn ?: "Description",
                price = it?.convertedPrice ?: 0.0,
                isAvailable = it?.isAvailable == 1,
                currencyCode = it?.currencyCode ?: "AED",
                imageUrl = it?.images?.firstOrNull()?.let { image ->
                    BASE_IMAGE_URL + image.path
                } ?: "",
            )
        } ?: emptyList(),
        parentProduct = data?.parent?.toUiModel(),
        isFavorite = data?.isFavorite ?: false,
        imagesList = data?.images?.map { BASE_IMAGE_URL + it?.path } ?: emptyList())}

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
        imageUrl = images?.firstOrNull()?.path ?: "",
    )
}

fun Parent.toUiModel(): ProductUiModel {
    return ProductUiModel(
        id = id ?: -1,
        name = nameEn ?: "Product Name", // or `nameAr` if using Arabic
        description = descriptionEn ?: "Description",
        price = if (discount == null) convertedPrice ?: 0.0 else discountedPrice ?: 0.0,
        isAvailable = isAvailable == 1,
        currencyCode = currencyCode ?: "AED",
        imageUrl = images?.firstOrNull()?.path ?: "",
    )


}