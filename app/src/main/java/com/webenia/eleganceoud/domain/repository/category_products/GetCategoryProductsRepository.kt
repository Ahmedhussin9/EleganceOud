package com.webenia.eleganceoud.domain.repository.category_products

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.category_product.CategoryProductResponse
import kotlinx.coroutines.flow.Flow

interface GetCategoryProductsRepository {
    fun getCategoryProducts(categoryId: Int, currency: String): Flow<Resource<CategoryProductResponse>>
}