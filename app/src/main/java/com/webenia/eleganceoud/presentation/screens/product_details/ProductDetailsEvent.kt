package com.webenia.eleganceoud.presentation.screens.product_details

import com.webenia.eleganceoud.domain.model.product.ProductAmountUiModel


sealed class ProductDetailsEvent {
    data class OnWeightSelected(
        val item: ProductAmountUiModel
    ) : ProductDetailsEvent()
}