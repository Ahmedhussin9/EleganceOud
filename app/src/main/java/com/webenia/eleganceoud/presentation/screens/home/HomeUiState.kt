package com.webenia.eleganceoud.presentation.screens.home

import com.webenia.eleganceoud.domain.model.brands.BrandUiModel
import com.webenia.eleganceoud.domain.model.category.CategoryUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.util.state.UiText

data class HomeUiState (
    val ourProductsList: List<ProductUiModel> = emptyList(),
    val categoriesList: List<CategoryUiModel> = emptyList(),
    val brandsList: List<BrandUiModel> = emptyList(),
    val isLoading:Boolean = true,
    val error: UiText? = null
    )