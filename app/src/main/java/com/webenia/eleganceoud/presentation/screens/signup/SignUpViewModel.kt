package com.webenia.eleganceoud.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.requests.resgister_request.RegisterRequest
import com.webenia.eleganceoud.domain.repository.auth.RegisterRepository
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {
    var uiState by mutableStateOf(SignUpUiState())
        private set

    private val _uiEvent = MutableSharedFlow<SignUpUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.NameChanged -> {
                uiState = uiState.copy(name = event.value, nameError = null)
            }

            is SignUpEvent.EmailChanged -> {
                uiState = uiState.copy(email = event.value, emailError = null)
            }

            is SignUpEvent.PhoneChanged -> {
                uiState = uiState.copy(phone = event.value, phoneError = null)
            }

            is SignUpEvent.PasswordChanged -> {
                uiState = uiState.copy(password = event.value, passwordError = null)
            }

            is SignUpEvent.ConfirmPasswordChanged -> {
                uiState = uiState.copy(confirmPassword = event.value, confirmPasswordError = null)
            }

            is SignUpEvent.ToggleTermsAndConditions -> {
                uiState = uiState.copy(
                    termsAndConditions = event.value
                )
            }

            is SignUpEvent.AlreadyHaveAccount -> {
                if (event.value) {
                    viewModelScope.launch {
                        sendUiEvent(SignUpUiEvent.Navigate(AppDestination.Login))
                    }
                }
            }

            is SignUpEvent.Submit -> {


                if (!validate()) return
                if (!uiState.termsAndConditions) {
                    viewModelScope.launch {
                        sendUiEvent(SignUpUiEvent.ShowToast(UiText.DynamicString("You must accept the Terms & Conditions")))
                    }
                    return
                }
                viewModelScope.launch(Dispatchers.IO) {
                    val request = RegisterRequest(
                        name = uiState.name,
                        email = uiState.email,
                        phone = uiState.phone,
                        password = uiState.password,
                        password_confirmation = uiState.confirmPassword,
                        country_id = "12",
                        role = "user"
                    )
                    repository.registerUser(
                        body = request
                    ).collect { state ->
                        when (state) {
                            is Resource.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    success = true,
                                    errorMessage = null
                                )
                                val user = state.data?.data?.user
                                if (user != null) {
                                    if (user.is_verified) {
                                        sendUiEvent(SignUpUiEvent.Navigate(AppDestination.Home))
                                    } else {
                                        sendUiEvent(SignUpUiEvent.Navigate(AppDestination.Otp(user.email)))
                                    }

                                }
                            }

                            is Resource.Error -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    success = false,
                                    errorMessage = state.message
                                )
                                sendUiEvent(
                                    SignUpUiEvent.ShowToast(
                                        state.message
                                            ?: UiText.DynamicString("Please try again later")
                                    )
                                )

                            }

                            is Resource.Loading -> {
                                uiState = uiState.copy(isLoading = true)
                            }
                        }
                    }

                }
            }
        }
    }

    private suspend fun sendUiEvent(event: SignUpUiEvent) {
        _uiEvent.emit(event)
    }

    private fun validate(): Boolean {
        var isValid = true

        val nameError = if (uiState.name.isBlank()) "Name is required" else null
        val emailError =
            if (!Patterns.EMAIL_ADDRESS.matcher(uiState.email).matches()) "Invalid email" else null
        val phoneError = if (uiState.phone.length < 8) "Phone is too short" else null
        val passwordError = if (uiState.password.length < 6) "Password too short" else null
        val confirmPasswordError =
            if (uiState.confirmPassword != uiState.password) "Passwords do not match" else null

        if (nameError != null || emailError != null || phoneError != null ||
            passwordError != null || confirmPasswordError != null
        ) {
            isValid = false
        }

        uiState = uiState.copy(
            nameError = nameError,
            emailError = emailError,
            phoneError = phoneError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError,
        )

        return isValid
    }

}

