package com.webenia.eleganceoud.domain.repository.product

import ProductDetailsResponse
import com.elegance_oud.util.state.Resource
import kotlinx.coroutines.flow.Flow

interface GetProductDetailsRepository {
    fun getProductDetails(
        currency: String,
        productId: Int
    ): Flow<Resource<ProductDetailsResponse>>

}