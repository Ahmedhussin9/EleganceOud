package com.webenia.eleganceoud.presentation.screens.category_products

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.category.CategoryUiEvents
import com.webenia.eleganceoud.util.state.UiText

sealed class CategoryProductUiEvents {
    data class Navigate(val destination: AppDestination) : CategoryProductUiEvents()
    data class ShowToast(val message: UiText) : CategoryProductUiEvents()
}