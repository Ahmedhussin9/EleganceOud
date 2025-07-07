package com.webenia.eleganceoud.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.mapper.toUiModel
import com.webenia.eleganceoud.domain.model.brands.toUiModel
import com.webenia.eleganceoud.domain.repository.home.GetHomeBrandsRepository
import com.webenia.eleganceoud.domain.repository.home.GetHomeCategoriesRepository
import com.webenia.eleganceoud.domain.repository.home.GetOurProductsRepository
import com.webenia.eleganceoud.presentation.screens.signin.SignInUiEvents
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getOurProductsRepository: GetOurProductsRepository,
    private val getHomeCategoriesRepository: GetHomeCategoriesRepository,
    private val getHomeBrandsRepository: GetHomeBrandsRepository
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    private var _uiEvent = MutableSharedFlow<HomeUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getOurProducts()
        getHomeCategories()
        getHomeBrands()
    }

    private fun getHomeBrands() {
        viewModelScope.launch(Dispatchers.IO) {
            getHomeBrandsRepository.getHomeBrands().collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = null,
                            brandsList =
                            if (state.data?.data?.isNotEmpty() == true) {
                                state.data.data.map {
                                    it.toUiModel()
                                }
                            } else {
                                emptyList()
                            }
                        )
                    }

                    is Resource.Error -> {
                        uiState = uiState.copy(isLoading = false)

                    }
                }
            }
        }
    }

    private fun getHomeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getHomeCategoriesRepository.getHomeCategories().collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = null,
                            categoriesList = if (state.data?.data?.isNotEmpty() == true) {
                                state.data.data.map {
                                    it.toUiModel()
                                }
                            } else {
                                emptyList()
                            })
                    }

                    is Resource.Error -> {
                        uiState = uiState.copy(isLoading = false)
                        sendUiEvent(
                            HomeUiEvents.ShowToast(
                                state.message ?: UiText.DynamicString("Try again later")
                            )
                        )
                    }
                }
            }
        }
    }

    private fun getOurProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getOurProductsRepository.getOurProducts().collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = null,
                            ourProductsList = if (
                                state.data?.data?.isNotEmpty() == true
                            ) state.data.data.map {
                                it.toUiModel()
                            } else emptyList()
                        )
                    }

                    is Resource.Error -> {
                        uiState = uiState.copy(isLoading = false)
                        sendUiEvent(
                            HomeUiEvents.ShowToast(
                                state.message ?: UiText.DynamicString("Try again later")
                            )
                        )

                    }


                }
            }
        }
    }

    private suspend fun sendUiEvent(event: HomeUiEvents) {
        _uiEvent.emit(event)
    }

}