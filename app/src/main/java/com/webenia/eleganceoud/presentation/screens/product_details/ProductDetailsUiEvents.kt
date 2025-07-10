package com.webenia.eleganceoud.presentation.screens.product_details

import com.webenia.eleganceoud.presentation.navigation.AppDestination

sealed class ProductDetailsUiEvents {
    data class Navigate(val destination: AppDestination) : ProductDetailsEvent()
    data class ShowToast(val message: String) : ProductDetailsEvent()
}