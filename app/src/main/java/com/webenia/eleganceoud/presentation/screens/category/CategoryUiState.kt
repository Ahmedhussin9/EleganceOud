package com.webenia.eleganceoud.presentation.screens.category

import com.webenia.eleganceoud.domain.model.category.CategoryUiModel
import com.webenia.eleganceoud.util.state.UiText

data class CategoryUiState (
    val error: UiText? = null,
    val isLoading:Boolean = true,
    val categoriesList: List<CategoryUiModel> = emptyList(),
)