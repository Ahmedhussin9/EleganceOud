package com.webenia.eleganceoud.presentation.screens.cart

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.category.CategoryUiEvents
import com.webenia.eleganceoud.util.state.UiText

sealed class CartUiEvents {
    data class Navigate(val destination: AppDestination): CartUiEvents()
    data class ShowToast(val message: UiText): CartUiEvents()
}