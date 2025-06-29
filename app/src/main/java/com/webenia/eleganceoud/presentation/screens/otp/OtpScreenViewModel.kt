package com.webenia.eleganceoud.presentation.screens.otp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.requests.resend_otp.ResendOtpRequest
import com.webenia.eleganceoud.domain.repository.OtpRepository
import com.webenia.eleganceoud.presentation.screens.signin.SignInUiEvents
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpScreenViewModel @Inject constructor(
    val repository: OtpRepository
) : ViewModel() {
    var uiState by mutableStateOf(OtpUiState())


    private val _timerSeconds = MutableStateFlow(0)
    val timerSeconds = _timerSeconds.asStateFlow()
    private var timerJob: Job? = null

    private var _uiEvent = MutableSharedFlow<OtpUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: OtpEvents) {
        when (event) {
            is OtpEvents.DigitEntered -> {
                //update otp
                uiState = uiState.copy(otp = event.digit)

            }

            is OtpEvents.SubmitOtp -> {
                //submit otp
            }

            is OtpEvents.ResendOtp -> {
                resendCode()
                viewModelScope.launch(Dispatchers.IO) {
                    val request = ResendOtpRequest(
                        email = uiState.email
                    )
                    repository.resendOtp(request).collect { state ->
                        when (state) {
                            is Resource.Success -> {
                                uiState = uiState.copy(
                                    isLoading = false
                                )
                                sendUiEvent(
                                    OtpUiEvent.ShowToast(
                                        UiText.DynamicString("Check your email")
                                    )
                                )

                            }

                            is Resource.Error -> {
                                uiState = uiState.copy(
                                    isLoading = false
                                )
                                sendUiEvent(
                                    OtpUiEvent.ShowToast(
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


    private fun resendCode() {
        // Trigger resend logic here (e.g., API call)
        startTimer()
    }

    private suspend fun sendUiEvent(event: OtpUiEvent) {
        _uiEvent.emit(event)
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch(Dispatchers.IO) {
            for (i in 120 downTo 0) {
                _timerSeconds.value = i
                delay(1000L)
            }
        }
    }

}