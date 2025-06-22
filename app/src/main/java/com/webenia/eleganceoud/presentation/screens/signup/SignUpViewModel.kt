package com.webenia.eleganceoud.presentation.screens.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.requests.resgister_request.RegisterRequest
import com.webenia.eleganceoud.domain.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: RegisterRepository
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
                viewModelScope.launch(Dispatchers.IO) {
                    val request = RegisterRequest(
                        name = uiState.name,
                        email = uiState.email,
                        phone = uiState.phone,
                        password = uiState.password,
                        password_confirmation = uiState.confirmPassword,
                        country_id = "12"
                    )
                    repository.registerUser(
                        body = request
                    ).collect { state->
                        when (state) {
                            is Resource.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    success = true,
                                    errorMessage = null
                                )
                            }

                            is Resource.Error -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                    success = false,
                                    errorMessage = state.message
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
}