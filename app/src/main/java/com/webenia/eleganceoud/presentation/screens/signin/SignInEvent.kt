package com.webenia.eleganceoud.presentation.screens.signin

sealed class SignInEvent {
    data class EmailChanged(val value: String) : SignInEvent()
    data class PasswordChanged(val value: String) : SignInEvent()
    object Submit : SignInEvent()
    object ForgotPassword : SignInEvent()
    object CreateAnAccount : SignInEvent()



}