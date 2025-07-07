package com.webenia.eleganceoud.domain.repository.home

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.home.best_sellings.HomeBestSellingResponse
import kotlinx.coroutines.flow.Flow

interface GetHomeBestSellingRepository {
    fun getHomeBestSellingProducts(): Flow<Resource<HomeBestSellingResponse>>
}