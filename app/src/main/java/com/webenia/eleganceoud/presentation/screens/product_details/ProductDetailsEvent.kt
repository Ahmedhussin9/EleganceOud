package com.webenia.eleganceoud.presentation.screens.product_details

import com.webenia.eleganceoud.domain.model.product.ProductAmountUiModel


sealed class ProductDetailsEvent {
    data class OnWeightSelected(
        val item: ProductAmountUiModel
    ) : ProductDetailsEvent()
    data class OnResetClick(
        val item: ProductAmountUiModel
    ): ProductDetailsEvent()
    class GetProductDetails(val productId: Int) : ProductDetailsEvent()
    object AddToCart : ProductDetailsEvent()
    object OnBackClick: ProductDetailsEvent()
     class ProductClicked(val productId: Int) : ProductDetailsEvent()

}