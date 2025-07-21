package com.webenia.eleganceoud.presentation.screens.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.domain.mapper.toUiModel
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
    private val repository: GetFavoritesRepository
) : ViewModel() {
    var uiState by mutableStateOf(FavoriteUiState())
        private set

    private var _uiEvent = MutableSharedFlow<FavoriteUiEvents>()
    val uiEvent = _uiEvent.asSharedFlow()

    init {
        getFavorites()
    }

    fun getFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavorites().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        uiState = uiState.copy(
                            products = resource.data?.getFavoritesResponse?.map {
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

    fun onEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.FavoriteClick -> {

            }

            is FavoriteEvent.ProductClicked -> {

            }

            is FavoriteEvent.GetFavorites -> {

            }

            is FavoriteEvent.AddToCartClick -> {

            }

            is FavoriteEvent.OnBackClick -> {

            }


        }
    }

    private suspend fun sendUiEvent(
        event: FavoriteUiEvents
    ) {
        _uiEvent.emit(event)
    }


}