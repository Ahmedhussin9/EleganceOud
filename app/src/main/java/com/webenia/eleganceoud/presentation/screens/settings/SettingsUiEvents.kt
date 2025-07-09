package com.webenia.eleganceoud.presentation.screens.settings

import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.signin.SignInUiEvents
import com.webenia.eleganceoud.util.state.UiText

sealed class SettingsUiEvents {
    data class Navigate(val destination: AppDestination): SettingsUiEvents()
    data class ShowToast(val message: UiText): SettingsUiEvents()
}