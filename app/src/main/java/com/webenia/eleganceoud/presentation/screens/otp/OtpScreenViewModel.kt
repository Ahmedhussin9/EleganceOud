package com.webenia.eleganceoud.presentation.screens.otp

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OtpScreenViewModel @Inject constructor() : ViewModel() {
    var uiState by mutableStateOf(OtpUiState())
        private set

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
                //resend otp
            }
            is OtpEvents.ResendCode -> {
                resendCode()
            }
        }
    }
    private val _timerSeconds = MutableStateFlow(0)
    val timerSeconds = _timerSeconds.asStateFlow()

    private var timerJob: Job? = null

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch (Dispatchers.IO){
            for (i in 60 downTo 0) {
                _timerSeconds.value = i
                delay(1000L)
            }
        }
    }

    fun resendCode() {
        // Trigger resend logic here (e.g., API call)
        startTimer()
    }

}