package com.webenia.eleganceoud.domain.repository.home

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.home.category.HomeCategoriesResponse
import kotlinx.coroutines.flow.Flow

interface GetHomeCategoriesRepository {
    fun getHomeCategories(): Flow<Resource<HomeCategoriesResponse>>
}