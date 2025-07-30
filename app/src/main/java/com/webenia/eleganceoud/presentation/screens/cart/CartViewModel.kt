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
import com.webenia.eleganceoud.presentation.screens.category.CategoryUiEvents
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
            is CartEvents.OnFavClicked -> {
                viewModelScope.launch(Dispatchers.IO) {
                    if (events.categoryProduct.isFavorite == false) {
                        addToFav(
                            events.categoryProduct.id
                        )
                    } else {
                        removeFromFav(
                            events.categoryProduct.id
                        )
                    }
                }
            }

            is CartEvents.OnPlusClicked -> {

            }

            is CartEvents.OnMinusClicked -> {
            }

            is CartEvents.OnDeleteClicked -> {
            }

            is CartEvents.OnCheckoutClicked -> {
            }

            is CartEvents.OnProductClicked -> {
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

    fun addToFav(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            addToFavRepository.addToFav(id).collect {
                when (it) {
                    is Resource.Loading -> {
                        uiState = uiState.copy()
                    }

                    is Resource.Success -> {
                        sendUiEvent(CartUiEvents.ShowToast(UiText.DynamicString("Added to favorites")))
                    }

                    is Resource.Error -> {
                        uiState = uiState.copy()
                    }

                }
            }
        }
    }

    fun removeFromFav(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavRepository.deleteFav(id).collect {
                when (it) {
                    is Resource.Loading -> {
                        uiState = uiState.copy()
                    }

                    is Resource.Success -> {
                        sendUiEvent(CartUiEvents.ShowToast(UiText.DynamicString("Removed from favorites")))
                    }

                    is Resource.Error -> {
                        uiState = uiState.copy()
                    }

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