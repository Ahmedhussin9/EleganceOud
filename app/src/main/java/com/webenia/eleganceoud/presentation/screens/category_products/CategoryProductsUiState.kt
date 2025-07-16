package com.webenia.eleganceoud.presentation.screens.category_products

import com.webenia.eleganceoud.domain.model.product.ProductUiModel

data class CategoryProductsUiState (
    val categoryProducts: List<ProductUiModel>? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val categoryName: String? = null

    )