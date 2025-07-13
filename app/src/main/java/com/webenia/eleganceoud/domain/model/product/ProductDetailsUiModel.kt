package com.webenia.eleganceoud.domain.model.product

import ParentProduct

data class ProductDetailsUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val isAvailable: Boolean,
    val mainImageUrl: String,
    val hasAmount : Boolean,
    val discount: String?,
    val amounts: List<ProductAmountUiModel>,
    val relatedProducts: List<ProductDetailsUiModel> = emptyList(),
    val parentProduct: ParentProduct? = null,
    val imagesList : List<String> = emptyList(),
    val priceAfterDiscount: Double,
    val currencyCode: String
)

data class ProductAmountUiModel(
    val weight: Int,
    val price: String,
    val unit: String
)

