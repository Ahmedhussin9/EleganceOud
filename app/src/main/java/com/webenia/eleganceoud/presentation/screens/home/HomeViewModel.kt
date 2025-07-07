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
import com.webenia.eleganceoud.domain.repository.home.GetHomeBestSellingRepository
import com.webenia.eleganceoud.domain.repository.home.GetHomeBrandsRepository
import com.webenia.eleganceoud.domain.repository.home.GetHomeCategoriesRepository
import com.webenia.eleganceoud.domain.repository.home.GetHomeLatestProductsRepository
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
    private val getHomeBrandsRepository: GetHomeBrandsRepository,
    private val getHomeBestSellingRepository: GetHomeBestSellingRepository,
    private val getHomeLatestProductsRepository: GetHomeLatestProductsRepository
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    private var _uiEvent = MutableSharedFlow<HomeUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getOurProducts()
        getHomeCategories()
        getHomeBrands()
        getHomeBestSellingProducts()
        getHomeLatestProducts()
    }

    private fun areAllRequestsDone(state: HomeUiState): Boolean {
        return state.ourProductsDone &&
                state.categoriesDone &&
                state.brandsDone &&
                state.bestSellingDone &&
                state.latestProductsDone
    }

    private fun getOurProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getOurProductsRepository.getOurProducts().collect { state ->
                when (state) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val newState = uiState.copy(
                            ourProductsList = state.data?.data?.map { it.toUiModel() } ?: emptyList(),
                            ourProductsDone = true
                        )
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                    }
                    is Resource.Error -> {
                        val newState = uiState.copy(ourProductsDone = true)
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                        sendUiEvent(HomeUiEvents.ShowToast(state.message ?: UiText.DynamicString("Try again later")))
                    }
                }
            }
        }
    }

    private fun getHomeCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            getHomeCategoriesRepository.getHomeCategories().collect { state ->
                when (state) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val newState = uiState.copy(
                            categoriesList = state.data?.data?.map { it.toUiModel() } ?: emptyList(),
                            categoriesDone = true
                        )
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                    }
                    is Resource.Error -> {
                        val newState = uiState.copy(categoriesDone = true)
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                        sendUiEvent(HomeUiEvents.ShowToast(state.message ?: UiText.DynamicString("Try again later")))
                    }
                }
            }
        }
    }

    private fun getHomeBrands() {
        viewModelScope.launch(Dispatchers.IO) {
            getHomeBrandsRepository.getHomeBrands().collect { state ->
                when (state) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val newState = uiState.copy(
                            brandsList = state.data?.data?.map { it.toUiModel() } ?: emptyList(),
                            brandsDone = true
                        )
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                    }
                    is Resource.Error -> {
                        val newState = uiState.copy(brandsDone = true)
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                    }
                }
            }
        }
    }

    private fun getHomeBestSellingProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getHomeBestSellingRepository.getHomeBestSellingProducts().collect { state ->
                when (state) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val newState = uiState.copy(
                            bestSellingList = state.data?.data?.map { it.toUiModel() } ?: emptyList(),
                            bestSellingDone = true
                        )
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                    }
                    is Resource.Error -> {
                        val newState = uiState.copy(bestSellingDone = true)
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                    }
                }
            }
        }
    }

    private fun getHomeLatestProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getHomeLatestProductsRepository.getHomeLatestProducts().collect { state ->
                when (state) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val newState = uiState.copy(
                            latestProductsList = state.data?.data?.map { it.toUiModel() } ?: emptyList(),
                            latestProductsDone = true
                        )
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                    }
                    is Resource.Error -> {
                        val newState = uiState.copy(latestProductsDone = true)
                        uiState = newState.copy(isLoading = !areAllRequestsDone(newState))
                    }
                }
            }
        }
    }

    private suspend fun sendUiEvent(event: HomeUiEvents) {
        _uiEvent.emit(event)
    }
}
