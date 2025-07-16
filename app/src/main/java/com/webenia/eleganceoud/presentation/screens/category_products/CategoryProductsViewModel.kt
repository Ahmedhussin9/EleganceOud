package com.webenia.eleganceoud.presentation.screens.category_products

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.mapper.toUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.domain.repository.category_products.GetCategoryProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryProductsViewModel @Inject constructor(
    private val getCategoryProductsRepository: GetCategoryProductsRepository
) : ViewModel() {
    var uiState by mutableStateOf(CategoryProductsUiState())
        private set


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
                        uiState = uiState.copy(isLoading = false)
                    }
                }
            }
        }

    }
}