package com.webenia.eleganceoud.presentation.screens.cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.mapper.toCartModel
import com.webenia.eleganceoud.domain.model.cart.CartModel
import com.webenia.eleganceoud.domain.repository.cart.GetCartRepository
import com.webenia.eleganceoud.domain.repository.fav.AddToFavRepository
import com.webenia.eleganceoud.domain.repository.fav.DeleteFavRepository
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartRepository: GetCartRepository,
    private val addToFavRepository: AddToFavRepository,
    private val deleteFavRepository: DeleteFavRepository
) : ViewModel() {
    var uiState by mutableStateOf(CartUiState())
        private set

    private var _uiEvent = MutableSharedFlow<CartUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onEvent(events: CartEvents) {
        when (events) {
            is CartEvents.OnCountChange -> {

            }

            is CartEvents.OnPlusClick -> {

            }

            is CartEvents.OnReloadClick -> {
                getCart()
                uiState = uiState.copy(
                    error = null
                )
            }

            is CartEvents.OnMinusClick -> {
            }

            is CartEvents.OnDeleteClick -> {
            }

            is CartEvents.OnCheckoutClicked -> {
            }

            is CartEvents.OnProductClick -> {
                viewModelScope.launch(
                    Dispatchers.IO
                ) {
                    sendUiEvent(
                        CartUiEvents.Navigate(
                            AppDestination.ProductDetails(
                                events.categoryProduct.id
                            )
                        )
                    )
                }
            }
        }
    }


    fun getCart() {
        viewModelScope.launch(Dispatchers.IO) {
            getCartRepository.getCartItems().collect {
                when (it) {
                    is Resource.Success -> {
                        val cartData = it.data?.toCartModel()
                        uiState = uiState.copy(
                            isLoading = false,
                            cartModel = cartData ?: CartModel(
                                totalPrice = 0.0,
                                totalQuantity = 0,
                                currencyCode = "",
                                cartItems = emptyList()
                            )
                        )

                    }

                    is Resource.Error -> {
                        uiState = uiState.copy(isLoading = false, error = it.message)
                        sendUiEvent(
                            CartUiEvents.ShowToast(
                                it.message
                                    ?: UiText.DynamicString("Try again later")
                            )
                        )
                    }

                    is Resource.Loading -> {
                        uiState = uiState.copy(isLoading = true)
                    }
                }
            }
        }
    }


    private suspend fun sendUiEvent(event: CartUiEvents) {
        _uiEvent.emit(event)
    }
}