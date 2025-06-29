package com.webenia.eleganceoud.presentation.screens.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.requests.login_request.SignInRequest
import com.webenia.eleganceoud.domain.repository.SignInRepository
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.signup.SignUpUiEvent
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

                viewModelScope.launch(Dispatchers.IO) {
                    val request = SignInRequest(
                        email = uiState.email,
                        password = uiState.password
                    )
                    repository.signInRequest(
                        body = request
                    ).collect { state ->
                        when (state) {
                            is Resource.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                )
                                val user = state.data?.user
                                if (user != null) {
                                    if (user.isVerified == 1) {
                                        sendUiEvent(SignInUiEvents.Navigate(AppDestination.Home))
                                    } else {
                                        sendUiEvent(SignInUiEvents.Navigate(AppDestination.Otp(user.email)))
                                    }

                                }
                            }

                            is Resource.Error -> {
                                uiState = uiState.copy(
                                    isLoading = false,
                                )
                                sendUiEvent(
                                    SignInUiEvents.ShowToast(
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