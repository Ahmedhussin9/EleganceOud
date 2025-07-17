package com.webenia.eleganceoud.domain.model.product

import Parent

data class ProductDetailsUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double?,
    val isAvailable: Boolean,
    val mainImageUrl: String,
    val hasAmount: Boolean,
    val discountValue: String?,
    val amounts: List<ProductAmountUiModel>,
    val relatedProducts: List<ProductUiModel> = emptyList(),
    val parentProduct: ProductUiModel? = null,
    val imagesList: List<String> = emptyList(),
    val priceAfterDiscount: Double?,
    val currencyCode: String
)

data class ProductAmountUiModel(
    val weight: Int,
    val price: String,
    val priceAfter: String?,
    val unit: String,
    val currencyCode: String
)

