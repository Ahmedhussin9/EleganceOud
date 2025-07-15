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
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.home.HomeUiEvents
import com.webenia.eleganceoud.presentation.screens.signin.SignInUiEvents
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsRepository: GetProductDetailsRepository
) : ViewModel() {
    var uiState by mutableStateOf(ProductDetailsUiState())
        private set

    private var _uiEvent = MutableSharedFlow<ProductDetailsUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.OnWeightSelected -> {
                uiState = uiState.copy(
                    selectedWeight = event.item,
                    productDetails = uiState.productDetails?.copy(
                        price = uiState.selectedWeight?.price?.toDouble() ?: 0.0,
                        priceAfterDiscount = uiState.selectedWeight?.priceAfter?.toDouble() ?: 0.0,
                    )
                )
            }

            is ProductDetailsEvent.GetProductDetails -> {

                getProductDetails(
                    productId = event.productId
                )

            }

            is ProductDetailsEvent.AddToCart -> {
                // Add to cart logic
            }

            is ProductDetailsEvent.OnBackClick -> {
                viewModelScope.launch {
                    sendUiEvent(ProductDetailsUiEvents.BackClicked)
                }
            }

            is ProductDetailsEvent.ProductClicked -> {

                viewModelScope.launch {
                    sendUiEvent(ProductDetailsUiEvents.Navigate(AppDestination.ProductDetails(event.productId)))
                }
            }

        }
    }


    //38 57
    private fun getProductDetails(
        productId: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            getProductDetailsRepository.getProductDetails(
                productId = productId,
                currency = "USD"
            ).collect { state ->
                when (state) {
                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            productDetails = state.data?.data?.toUiModel()
                        )


                    }

                    is Resource.Error -> {
                        uiState = uiState.copy(isLoading = false)
                        sendUiEvent(
                            ProductDetailsUiEvents.ShowToast(
                                state.message ?: UiText.DynamicString(
                                    "Something went wrong"
                                )
                            )
                        )
                    }

                }
            }

        }
    }

    private suspend fun sendUiEvent(event: ProductDetailsUiEvents) {
        _uiEvent.emit(event)
    }
}