package com.webenia.eleganceoud.domain.repository.home

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.home.category.CategoriesResponse
import kotlinx.coroutines.flow.Flow

interface GetCategoriesRepository {
    fun getCategories(): Flow<Resource<CategoriesResponse>>
}