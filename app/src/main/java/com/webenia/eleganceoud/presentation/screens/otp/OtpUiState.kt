package com.webenia.eleganceoud.presentation.screens.otp

data class OtpUiState (
    val otp: String="",
    val email: String="",
    val isOtpComplete: Boolean = false,
    val isLoading: Boolean = false,

)
