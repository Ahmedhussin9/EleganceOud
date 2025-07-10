package com.webenia.eleganceoud.domain.model.product

import ParentProduct

data class ProductDetailsUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val isAvailable: Boolean,
    val mainImageUrl: String,
    val discount: String?,
    val amounts: List<ProductAmountUiModel>,
    val relatedProducts: List<ProductDetailsUiModel> = emptyList(),
    val parentProduct: ParentProduct? = null,
    val imagesList : List<String> = emptyList()
)

data class ProductAmountUiModel(
    val weight: Int,
    val price: String,
    val unit: String
)

