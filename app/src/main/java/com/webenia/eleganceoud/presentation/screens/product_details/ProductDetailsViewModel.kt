package com.webenia.eleganceoud.presentation.screens.product_details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.mapper.toUiModel
import com.webenia.eleganceoud.domain.repository.product.GetProductDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsRepository: GetProductDetailsRepository
) : ViewModel() {
    var uiState by mutableStateOf(ProductDetailsUiState())
        private set

    init {
        getProductDetails()
    }

    private fun getProductDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductDetailsRepository.getProductDetails(
                productId = 57,
                currency = "USD"
            ).collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        Log.e("amr", state.data?.data.toString(), )
                        uiState = uiState.copy(
                            isLoading = false,
                            productDetails = state.data?.data?.toUiModel()
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