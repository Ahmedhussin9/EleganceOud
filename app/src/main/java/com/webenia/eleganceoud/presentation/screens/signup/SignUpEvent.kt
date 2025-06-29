package com.webenia.eleganceoud.presentation.screens.signup

sealed class SignUpEvent {
    data class NameChanged(val value: String) : SignUpEvent()
    data class EmailChanged(val value: String) : SignUpEvent()
    data class PhoneChanged(val value: String) : SignUpEvent()
    data class PasswordChanged(val value: String) : SignUpEvent()
    data class ConfirmPasswordChanged(val value: String) : SignUpEvent()
    data class ToggleTermsAndConditions(val value: Boolean) : SignUpEvent()
    data class AlreadyHaveAccount(val value: Boolean) : SignUpEvent()
    object Submit : SignUpEvent()
}