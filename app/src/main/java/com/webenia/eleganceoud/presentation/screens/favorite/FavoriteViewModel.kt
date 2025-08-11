package com.webenia.eleganceoud.presentation.screens.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.mapper.toUiModel
import com.webenia.eleganceoud.domain.repository.cart.AddToCartRepository
import com.webenia.eleganceoud.domain.repository.fav.DeleteFavRepository
import com.webenia.eleganceoud.domain.repository.fav.GetFavoritesRepository
import com.webenia.eleganceoud.util.state.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesRepository: GetFavoritesRepository,
    private val removeFavoritesRepository: DeleteFavRepository,
    private val addToCart: AddToCartRepository
) : ViewModel() {
    var uiState by mutableStateOf(FavoriteUiState())
        private set

    private var _uiEvent = MutableSharedFlow<FavoriteUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesRepository.getFavorites().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            products = resource.data?.favorites?.map {
                                it?.toUiModel()
                            },
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        val errorMessage = resource.message ?: UiText.DynamicString(
                            "Something went wrong"
                        )
                        uiState = uiState.copy(
                            error = errorMessage,
                            isLoading = false
                        )
                        sendUiEvent(FavoriteUiEvents.ShowToast(errorMessage))

                    }

                    is Resource.Loading -> {
                        uiState = uiState.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    fun removeFavorite(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFavoritesRepository.deleteFav(
                productId = productId
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        getFavorites()
                    }

                    is Resource.Error -> {
                        val errorMessage = it.message ?: UiText.DynamicString(
                            "Something went wrong"
                        )
                        uiState = uiState.copy(
                            error = errorMessage,
                            isLoading = false
                        )
                        sendUiEvent(FavoriteUiEvents.ShowToast(errorMessage))
                    }

                    is Resource.Loading -> {
                        uiState = uiState.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    fun addToCart(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            addToCart.addToCart(
                productId = productId,
                quantity = 1
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        sendUiEvent(FavoriteUiEvents.ShowToast(UiText.DynamicString("Added to cart")))
                        uiState = uiState.copy(
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        val errorMessage = it.message ?: UiText.DynamicString(
                            "Something went wrong"
                        )
                        uiState = uiState.copy(
                            error = errorMessage,
                            isLoading = false
                        )
                        sendUiEvent(FavoriteUiEvents.ShowToast(errorMessage))
                    }

                    is Resource.Loading -> {
                        uiState = uiState.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }


    fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.FavoriteClick -> {
                removeFavorite(event.productId)
            }

            is FavoriteEvent.ProductClicked -> {

            }

            is FavoriteEvent.GetFavorites -> {

            }

            is FavoriteEvent.AddToCartClick -> {
                addToCart(event.productId)
            }

            is FavoriteEvent.OnBackClick -> {

            }
            is FavoriteEvent.OnReloadClicked->{
                getFavorites()
                uiState = uiState.copy(
                    error = null
                )
            }


        }
    }

    private suspend fun sendUiEvent(
        event: FavoriteUiEvents
    ) {
        _uiEvent.emit(event)
    }


}