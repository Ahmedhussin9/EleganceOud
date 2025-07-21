package com.webenia.eleganceoud.presentation.screens.favorite

import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.util.state.UiText

data class FavoriteUiState(
    val products: List<ProductUiModel?>? = emptyList(),
    val error: UiText? = null,
    val isLoading: Boolean = false,
)