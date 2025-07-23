package com.webenia.eleganceoud.presentation.screens.category_products

import com.webenia.eleganceoud.domain.model.product.ProductUiModel

sealed class CategoryProductEvents {
    data class OnCategoryProductClicked(
        val categoryProduct: ProductUiModel
    ) : CategoryProductEvents()
    data class OnAddToCartClicked(
        val categoryProduct: ProductUiModel
    ) : CategoryProductEvents()
    data class OnFavClicked(
        val categoryProduct: ProductUiModel
    ):CategoryProductEvents()

}