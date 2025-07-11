package com.webenia.eleganceoud.presentation.screens.product_details

import ProductDetailsResponse
import com.webenia.eleganceoud.domain.model.product.ProductDetailsUiModel

data class ProductDetailsUiState (
    val productDetails: ProductDetailsUiModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null,

    )