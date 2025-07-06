package com.webenia.eleganceoud.domain.model.product

data class ProductUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String?,
    val currencyCode: String,
    val isAvailable: Boolean,
    val parentName: String? = null,
    val hasAmounts: Boolean = false
)
