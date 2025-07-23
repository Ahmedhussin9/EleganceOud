package com.webenia.eleganceoud.presentation.screens.product_details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.mapper.toUiModel
import com.webenia.eleganceoud.domain.repository.fav.AddToFavRepository
import com.webenia.eleganceoud.domain.repository.fav.DeleteFavRepository
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
    private val getProductDetailsRepository: GetProductDetailsRepository,
    private val addToFavRepository: AddToFavRepository,
    private val deleteFavRepository: DeleteFavRepository
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
                        price = event.item.price.toDoubleOrNull(),
                        priceAfterDiscount = event.item.priceAfter?.toDoubleOrNull()
                    )
                )
            }

            is ProductDetailsEvent.OnResetClick -> {
                uiState = uiState.copy(
                    selectedWeight = event.item,
                    productDetails = uiState.productDetails?.copy(
                        price = event.item.price.toDoubleOrNull(),
                        priceAfterDiscount = event.item.priceAfter?.toDoubleOrNull()
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

            is ProductDetailsEvent.OnAddToFavFavClick -> {
                // Add to favorites logic
                addToFav(
                    productId = event.itemId
                )

            }
            is ProductDetailsEvent.OnDeleteFavClick -> {
                deleteFav(
                    productId = event.itemId
                )
            }

        }
    }

    private fun addToFav(
        productId: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavRepository.addToFav(
                productId = productId
            ).collect { state ->
                uiState = when (state) {
                    is Resource.Loading -> {
                        uiState.copy()
                    }

                    is Resource.Success -> {
                        sendUiEvent(ProductDetailsUiEvents.ShowToast(UiText.DynamicString("Added from favorites")))
                        uiState.copy(
                            productDetails = uiState.productDetails?.copy(
                                isFavorite = true
                            )
                        )
                    }

                    is Resource.Error -> {
                        uiState.copy()

                    }
                }
            }
        }
    }
    private fun deleteFav(productId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavRepository.deleteFav(
                productId = productId
            ).collect{
                when(it){
                    is Resource.Loading -> {
                        uiState = uiState.copy()
                    }
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            productDetails = uiState.productDetails?.copy(
                                isFavorite = false
                            )

                        )
                        sendUiEvent(ProductDetailsUiEvents.ShowToast(UiText.DynamicString("Removed from favorites")))
                    }
                    is Resource.Error -> {
                        uiState = uiState.copy()
                        sendUiEvent(ProductDetailsUiEvents.ShowToast(it.message?:UiText.DynamicString("Something went wrong")))
                    }
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
                            productDetails = state.data?.toUiModel()
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