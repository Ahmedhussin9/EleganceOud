package com.webenia.eleganceoud.data.remote.repositroy.home

import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.response.home.our_products.ProductsResponse
import com.webenia.eleganceoud.domain.repository.home.GetOurProductsRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetOurProductsRepositoryImpl @Inject constructor(
    private val webServices: WebServices
): GetOurProductsRepository {
    override fun getOurProducts(): Flow<Resource<ProductsResponse>> {
        return toResultFlow {
            webServices.getOurProducts()
        }.map {state->
            when(state){
                is ApiState.Success -> Resource.Success(state.data)
                is ApiState.Error -> Resource.Error(state.message?: UiText.DynamicString("Try again later"))
                is ApiState.Loading -> Resource.Loading()
            }
        }
    }
}