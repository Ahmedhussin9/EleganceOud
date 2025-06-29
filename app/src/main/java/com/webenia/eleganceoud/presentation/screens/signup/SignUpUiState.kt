package com.webenia.eleganceoud.presentation.screens.signup

import com.webenia.eleganceoud.util.state.UiText

data class SignUpUiState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val termsAndConditions: Boolean = false,

    val nameError: String? = null,
    val emailError: String? = null,
    val phoneError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,

    val isLoading: Boolean = false,
    val success: Boolean = false,
    val errorMessage: UiText? = null
)