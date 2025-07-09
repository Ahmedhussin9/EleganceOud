package com.webenia.eleganceoud.presentation.screens.category

import com.webenia.eleganceoud.domain.model.category.CategoryUiModel

sealed class CategoryEvent {
     data class onCategoryClicked(
        val category: CategoryUiModel
    ) : CategoryEvent()
}