package com.webenia.eleganceoud.domain.repository.home

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.home.latest_products.HomeLatestProductsResponse
import kotlinx.coroutines.flow.Flow

interface GetHomeLatestProductsRepository {
    suspend fun getHomeLatestProducts(): Flow<Resource<HomeLatestProductsResponse>>
}