package com.webenia.eleganceoud.presentation.screens.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf(SignInUiState())
        private set

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> {
                uiState = uiState.copy(email = event.value)
            }

            is SignInEvent.PasswordChanged -> {
                uiState = uiState.copy(password = event.value)
            }


        }
    }

}