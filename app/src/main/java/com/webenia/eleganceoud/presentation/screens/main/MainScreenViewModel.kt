package com.webenia.eleganceoud.presentation.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webenia.eleganceoud.presentation.screens.signup.SignUpUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(

) : ViewModel() {
    private val _uiSEvent = MutableSharedFlow<MainScreenUiEvents>()
    val uiEvent = _uiSEvent.asSharedFlow()

    private val _selectedIndex = mutableIntStateOf(0)
    val selectedIndex: State<Int> = _selectedIndex

    fun onChipSelected(index: Int) {
        _selectedIndex.intValue = index
        viewModelScope.launch {
            _uiSEvent.emit(MainScreenUiEvents.OnChipSelected(index))
        }
    }

    private suspend fun sendUiEvent(event: MainScreenUiEvents) {
        _uiSEvent.emit(event)
    }
}