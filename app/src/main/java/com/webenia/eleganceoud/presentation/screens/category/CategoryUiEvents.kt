package com.webenia.eleganceoud.presentation.screens.category

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.UiText

sealed class CategoryUiEvents {
    data class Navigate(val destination: AppDestination): CategoryUiEvents()
    data class ShowToast(val message: UiText): CategoryUiEvents()
}