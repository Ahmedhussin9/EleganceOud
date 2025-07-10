package com.webenia.eleganceoud.presentation.screens.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.UserUtil
import com.webenia.eleganceoud.data.remote.requests.login_request.SignInRequest
import com.webenia.eleganceoud.data.remote.response.auth.signin.LoginResponse
import com.webenia.eleganceoud.domain.repository.auth.SignInRepository
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val repository: SignInRepository
) : ViewModel() {
    var uiState by mutableStateOf(SignInUiState())
        private set

    private var _uiEvent = MutableSharedFlow<SignInUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> {
                uiState = uiState.copy(email = event.value)
            }

            is SignInEvent.PasswordChanged -> {
                uiState = uiState.copy(password = event.value)
            }

            is SignInEvent.Submit -> {
                val request = SignInRequest(
                    email = uiState.email,
                    password = uiState.password
                )

                viewModelScope.launch(Dispatchers.IO) {
                    repository.signInRequest(
                        email = request.email,
                        password = request.password
                    ).collect { state ->

                        when (state) {
                            is ApiState.Loading -> {
                                uiState = uiState.copy(isLoading = true)
                            }

                            is ApiState.Success -> {
                                uiState = uiState.copy(isLoading = false)

                                when (state.data) {
                                    is LoginResponse.Success -> {
                                        UserUtil.saveIsLogin(true)
                                        UserUtil.saveToken(state.data.token)
                                        sendUiEvent(SignInUiEvents.Navigate(AppDestination.Main))
                                    }

                                    is LoginResponse.Unverified -> {
                                        sendUiEvent(
                                            SignInUiEvents.Navigate(
                                                AppDestination.Otp(
                                                    uiState.email
                                                )
                                            )
                                        )
                                    }

                                    else -> {
                                        sendUiEvent(SignInUiEvents.ShowToast(UiText.DynamicString("Something went wrong")))
                                    }
                                }
                            }

                            is ApiState.Error -> {
                                uiState = uiState.copy(isLoading = false)
                                sendUiEvent(
                                    SignInUiEvents.ShowToast(
                                        state.message
                                            ?: UiText.DynamicString("Please try again later")
                                    )
                                )
                            }
                        }
                    }
                }
            }

            is SignInEvent.ForgotPassword -> {
                viewModelScope.launch {
                    sendUiEvent(SignInUiEvents.Navigate(AppDestination.ForgotPassword))
                }

            }

            is SignInEvent.CreateAnAccount -> {
                viewModelScope.launch {
                    sendUiEvent(SignInUiEvents.Navigate(AppDestination.SignUp))
                }

            }
        }
    }

    private suspend fun sendUiEvent(event: SignInUiEvents) {
        _uiEvent.emit(event)
    }
}