package com.webenia.eleganceoud.presentation.screens.signup

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.UiText

sealed class SignUpUiEvent {
    data class Navigate(val destination: AppDestination): SignUpUiEvent()
    data class ShowToast(val message: UiText): SignUpUiEvent()
}