package com.webenia.eleganceoud.data.remote.repositroy.product

import ProductResponse
import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.domain.repository.product.GetProductDetailsRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetProductDetailsRepositoryImpl @Inject
constructor(
    private val webServices: WebServices
) : GetProductDetailsRepository {
    override fun getProductDetails(
        currency: String,
        productId: Int
    ): Flow<Resource<ProductResponse>> {
        return toResultFlow {
            webServices.getProductDetails(currency, productId)
        }.map { apiState ->
            when (apiState) {
                is ApiState.Success -> {
                    Resource.Success(apiState.data)
                }

                is ApiState.Error -> {
                    Resource.Error(
                        apiState.message ?: UiText.DynamicString("Something went wrong")
                    )
                }

                is ApiState.Loading -> {
                    Resource.Loading()
                }

            }
        }
    }
}