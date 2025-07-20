package com.webenia.eleganceoud.domain.model.product

data class ProductUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String?,
    val currencyCode: String,
    val isAvailable: Boolean,
    val parentName: String? = null,
    val hasAmounts: Boolean = false,
    val hasDiscount: Boolean = false,
    val discount: Double? = null,
    val priceAfterDiscount: Double? = null,
    val isFavorite: Boolean?=null,
)
