package com.webenia.eleganceoud.presentation.screens.favorite

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.UiText

sealed class FavoriteUiEvents {
    data class Navigate(val destination: AppDestination) : FavoriteUiEvents()
    data class ShowToast(val message: UiText) : FavoriteUiEvents()

}