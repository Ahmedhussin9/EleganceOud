package com.webenia.eleganceoud.presentation.screens.otp

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.UiText

sealed class OtpUiEvent {
    data class Navigate(val destination: AppDestination): OtpUiEvent()
    data class ShowToast(val message: UiText): OtpUiEvent()
}