package com.webenia.eleganceoud.presentation.screens.signup

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)