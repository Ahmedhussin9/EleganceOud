package com.webenia.eleganceoud.presentation.screens.signin

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,

    val emailError: String? = null,
    val passwordError: String? = null,

    )