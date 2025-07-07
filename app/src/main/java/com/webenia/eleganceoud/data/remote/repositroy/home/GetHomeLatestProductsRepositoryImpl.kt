package com.webenia.eleganceoud.data.remote.repositroy.home

import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.response.home.latest_products.HomeLatestProductsResponse
import com.webenia.eleganceoud.domain.repository.home.GetHomeLatestProductsRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHomeLatestProductsRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : GetHomeLatestProductsRepository {
    override suspend fun getHomeLatestProducts(): Flow<Resource<HomeLatestProductsResponse>> {
        return toResultFlow {
            webServices.getHomeLatestProducts()
        }.map { state ->
            when (state) {
                is ApiState.Loading ->
                    Resource.Loading()

                is ApiState.Success ->
                    Resource.Success(state.data)

                is ApiState.Error ->
                    Resource.Error(
                        state.message ?: UiText.DynamicString(
                            "Something went wrong"
                        )
                    )
            }
        }
    }
}