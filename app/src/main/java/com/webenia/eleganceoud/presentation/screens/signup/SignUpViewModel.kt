package com.webenia.eleganceoud.presentation.screens.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {
    var uiState by mutableStateOf(SignUpUiState())
        private set

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.NameChanged -> {
                uiState = uiState.copy(name = event.value)
            }
            is SignUpEvent.EmailChanged -> {
                uiState = uiState.copy(email = event.value)
            }
            is SignUpEvent.PhoneChanged -> {
                uiState = uiState.copy(phone = event.value)
            }
            is SignUpEvent.PasswordChanged -> {
                uiState = uiState.copy(password = event.value)
            }
            is SignUpEvent.ConfirmPasswordChanged -> {
                uiState = uiState.copy(confirmPassword = event.value)
            }
            is SignUpEvent.ToggleTermsAndConditions -> {
                uiState = uiState.copy(
                    termsAndConditions = event.value
                )
            }
            is SignUpEvent.AlreadyHaveAnAccount -> {
                // Navigate to login screen
            }
            is SignUpEvent.Submit -> {
                // Submit logic (validation, API call, etc.)
            }
        }
    }
}