package com.webenia.eleganceoud.presentation.screens.product_details

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.UiText

sealed class ProductDetailsUiEvents {
    data class Navigate(val destination: AppDestination) : ProductDetailsUiEvents()
    data class ShowToast(val message: UiText) : ProductDetailsUiEvents()
    object BackClicked : ProductDetailsUiEvents()
}