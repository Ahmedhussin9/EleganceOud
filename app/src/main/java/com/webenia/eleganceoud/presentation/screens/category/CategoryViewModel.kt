package com.webenia.eleganceoud.presentation.screens.category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.constraintlayout.compose.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.mapper.toUiModel
import com.webenia.eleganceoud.domain.repository.home.GetCategoriesRepository
import com.webenia.eleganceoud.presentation.screens.home.HomeUiEvents
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: GetCategoriesRepository
) : ViewModel() {
    var uiState by mutableStateOf(CategoryUiState())
        private set

    private var _uiEvent = MutableSharedFlow<CategoryUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getCategories()
    }
    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCategories().collect { categoriesResource ->
                when (categoriesResource) {
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            categoriesList = categoriesResource.data?.data?.map { it.toUiModel() } ?: emptyList(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        uiState = uiState.copy(error = categoriesResource.message)
                        sendUiEvent(CategoryUiEvents.ShowToast(categoriesResource.message?: UiText.DynamicString("Try again later")))
                    }

                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }
                }
            }
        }

    }
    private suspend fun sendUiEvent(event: CategoryUiEvents) {
        _uiEvent.emit(event)
    }
}