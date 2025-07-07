package com.webenia.eleganceoud.presentation.screens.home

import com.webenia.eleganceoud.domain.model.brands.BrandUiModel
import com.webenia.eleganceoud.domain.model.category.CategoryUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel
import com.webenia.eleganceoud.util.state.UiText

data class HomeUiState (
    val error: UiText? = null,
    val isLoading:Boolean = true,

    val ourProductsList: List<ProductUiModel> = emptyList(),
    val categoriesList: List<CategoryUiModel> = emptyList(),
    val brandsList: List<BrandUiModel> = emptyList(),
    val bestSellingList: List<ProductUiModel> = emptyList(),
    val latestProductsList: List<ProductUiModel> = emptyList(),

    val ourProductsDone: Boolean = false,
    val categoriesDone: Boolean = false,
    val brandsDone: Boolean = false,
    val bestSellingDone: Boolean = false,
    val latestProductsDone: Boolean = false
)