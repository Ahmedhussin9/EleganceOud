package com.webenia.eleganceoud.presentation.screens.home

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.UiText

sealed class HomeUiEvents {
    data class Navigate(val destination: AppDestination): HomeUiEvents()
    data class ShowToast(val message: UiText): HomeUiEvents()
}