package com.webenia.eleganceoud.presentation.screens.product_details

import com.webenia.eleganceoud.domain.model.product.ProductAmountUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel


sealed class ProductDetailsEvent {
    data class OnWeightSelected(
        val item: ProductAmountUiModel
    ) : ProductDetailsEvent()
    data class OnResetClick(
        val item: ProductAmountUiModel
    ): ProductDetailsEvent()
    data class OnFavClick(
        val itemId: Int
    ):ProductDetailsEvent()
    class GetProductDetails(val productId: Int) : ProductDetailsEvent()
    object AddToCart : ProductDetailsEvent()
    object OnBackClick: ProductDetailsEvent()
     class ProductClicked(val productId: Int) : ProductDetailsEvent()

}