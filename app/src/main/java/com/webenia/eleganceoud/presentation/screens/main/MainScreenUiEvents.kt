package com.webenia.eleganceoud.presentation.screens.main

sealed class MainScreenUiEvents {
    data class OnChipSelected(val index: Int) : MainScreenUiEvents()

}