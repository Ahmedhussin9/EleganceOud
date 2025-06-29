package com.webenia.eleganceoud.presentation.screens.signin

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.UiText

sealed class SignInUiEvents {
    data class Navigate(val destination: AppDestination): SignInUiEvents()
    data class ShowToast(val message: UiText): SignInUiEvents()
}