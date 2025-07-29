package com.webenia.eleganceoud.presentation.screens.cart

import com.webenia.eleganceoud.domain.model.cart.CartModel
import com.webenia.eleganceoud.util.state.UiText

data class CartUiState(
    val error: UiText? = null,
    val isLoading: Boolean = true,
    val cartModel: CartModel = CartModel(
        totalPrice = 0.0,
        totalQuantity = 0,
        currencyCode = "",
        cartItems = emptyList()
    ),
)