package com.webenia.eleganceoud.presentation.screens.home

import com.webenia.eleganceoud.domain.model.category.CategoryUiModel
import com.webenia.eleganceoud.domain.model.product.ProductUiModel

sealed class HomeEvents(){
    data class ProductClicked(
        val product: ProductUiModel
    ) : HomeEvents()
    data class CategoryClicked(
        val category: CategoryUiModel
    ) : HomeEvents()
}