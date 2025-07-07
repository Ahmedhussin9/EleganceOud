package com.webenia.eleganceoud.domain.repository.home

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.home.brands.HomeBrandsResponse
import kotlinx.coroutines.flow.Flow

interface GetHomeBrandsRepository {
    fun getHomeBrands(): Flow<Resource<HomeBrandsResponse>>
}