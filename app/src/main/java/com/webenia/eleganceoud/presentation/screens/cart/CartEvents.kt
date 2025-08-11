package com.webenia.eleganceoud.presentation.screens.cart

import com.webenia.eleganceoud.domain.model.cart.CartItemModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

sealed class CartEvents {
    data class OnPlusClick( val categoryProduct: CartItemModel) : CartEvents()
    data class OnMinusClick( val categoryProduct: CartItemModel) : CartEvents()
    data class OnDeleteClick( val categoryProduct: CartItemModel) : CartEvents()
    data class OnCountChange(val productId: Int, val newCount: Int) : CartEvents()
    object OnReloadClick:CartEvents()

    data class OnProductClick(val categoryProduct: CartItemModel):CartEvents()
    object OnCheckoutClicked : CartEvents()


}