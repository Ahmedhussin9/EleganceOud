package com.webenia.eleganceoud.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.UserUtil
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.model.User
import com.webenia.eleganceoud.domain.repository.auth.SignOutRepository
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.signin.SignInUiEvents
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val signOutRepository: SignOutRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiStates())
    val uiState = _uiState.asStateFlow()

    private var _uiEvent = MutableSharedFlow<SettingsUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SignOut -> {
                signOut()
            }
        }
    }

    fun signOut() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = UserUtil.getToken()
            signOutRepository.signOut(
                token = token ?: ""
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        UserUtil.clearToken()
                        UserUtil.saveIsLogin(false)
                        sendUiEvent(SettingsUiEvents.Navigate(AppDestination.Login))
                        sendUiEvent(SettingsUiEvents.ShowToast(UiText.DynamicString("Signed out successfully")))
                        _uiState.value = _uiState.value.copy(
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        sendUiEvent(SettingsUiEvents.ShowToast(UiText.DynamicString("Something went wrong")))
                        _uiState.value = _uiState.value.copy(
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = true
                        )
                    }
                }
            }

        }
    }

    private suspend fun sendUiEvent(event: SettingsUiEvents) {
        _uiEvent.emit(event)
    }
}