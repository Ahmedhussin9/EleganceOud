package com.webenia.eleganceoud.presentation.screens.category_products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.mapper.toUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.domain.repository.category_products.GetCategoryProductsRepository
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.category.CategoryUiEvents
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryProductsViewModel @Inject constructor(
    private val getCategoryProductsRepository: GetCategoryProductsRepository
) : ViewModel() {
    var uiState by mutableStateOf(CategoryProductsUiState())
        private set

    private var _uiEvent = MutableSharedFlow<CategoryProductUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun onEvent(events: CategoryProductEvents){
        when(events){
            is CategoryProductEvents.OnCategoryProductClicked -> {
                viewModelScope.launch (
                    Dispatchers.IO
                ){
                    sendUiEvent(CategoryProductUiEvents.Navigate(AppDestination.ProductDetails(events.categoryProduct.id)))
                }
            }
        }
    }


    fun getCategoryProducts(
        categoryId: Int,
        currency: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoryProductsRepository.getCategoryProducts(
                categoryId = categoryId,
                currency = currency
            ).collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            categoryName = state.data?.data?.name,
                            categoryProducts = state.data?.data?.products?.map {
                                it?.toUiModel() ?: ProductUiModel(
                                    id = 0,
                                    name = "",
                                    description = "",
                                    price = 0.0,
                                    imageUrl = "",
                                    currencyCode = "",
                                    isAvailable = false,
                                    parentName = "",
                                    hasAmounts = false,
                                    hasDiscount = false,
                                    discount = 0.0,

                                    )
                            }
                        )
                    }

                    is Resource.Error -> {
                        sendUiEvent(CategoryProductUiEvents.ShowToast(state.message?: UiText.DynamicString("Success")))
                        uiState = uiState.copy(isLoading = false)
                    }
                }
            }
        }

    }
    private suspend fun sendUiEvent(event: CategoryProductUiEvents) {
        _uiEvent.emit(event)
    }
}