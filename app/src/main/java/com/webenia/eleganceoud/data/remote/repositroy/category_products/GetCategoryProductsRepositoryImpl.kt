package com.webenia.eleganceoud.data.remote.repositroy.category_products

import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.response.category_product.CategoryProductResponse
import com.webenia.eleganceoud.domain.repository.category_products.GetCategoryProductsRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCategoryProductsRepositoryImpl @Inject constructor(
    private val webServices: WebServices
) : GetCategoryProductsRepository {
    override fun getCategoryProducts(
        categoryId: Int,
        currency: String
    ): Flow<Resource<CategoryProductResponse>> {
        return toResultFlow {
            webServices.getCategoryProducts(categoryId = categoryId, currency = currency)
        }.map { state ->
            when (state) {
                is ApiState.Error -> Resource.Error(
                    state.message ?: UiText.DynamicString("Something went wrong")
                )
                is ApiState.Loading -> Resource.Loading()
                is ApiState.Success -> Resource.Success(state.data)

            }
        }
    }
}