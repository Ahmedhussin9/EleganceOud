package com.webenia.eleganceoud.domain.repository.home

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.home.our_products.ProductsResponse
import kotlinx.coroutines.flow.Flow

interface GetOurProductsRepository {
    fun getOurProducts(): Flow<Resource<ProductsResponse>>
}