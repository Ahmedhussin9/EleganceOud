package com.webenia.eleganceoud.presentation.screens.cart

import com.webenia.eleganceoud.domain.model.product.ProductUiModel

sealed class CartEvents {
    data class OnFavClicked( val categoryProduct: ProductUiModel) : CartEvents()
    data class OnPlusClicked( val categoryProduct: ProductUiModel) : CartEvents()
    data class OnMinusClicked( val categoryProduct: ProductUiModel) : CartEvents()
    data class OnDeleteClicked( val categoryProduct: ProductUiModel) : CartEvents()
    data class OnProductClicked(val categoryProduct: ProductUiModel):CartEvents()
    object OnCheckoutClicked : CartEvents()


}