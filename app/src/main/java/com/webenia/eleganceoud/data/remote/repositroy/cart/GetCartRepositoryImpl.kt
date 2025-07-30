package com.webenia.eleganceoud.data.remote.repositroy.cart

import com.elegance_oud.util.UserUtil
import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.response.cart.get_cart.GetCartResponse
import com.webenia.eleganceoud.domain.repository.cart.GetCartRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCartRepositoryImpl @Inject constructor(
    val webServices: WebServices
):GetCartRepository {
    override fun getCartItems(): Flow<Resource<GetCartResponse>> {
        return toResultFlow {
            webServices.getCartItems(
                token = "Bearer ${UserUtil.getToken()}"
            )
        }.map { state->
            when (state){
                is ApiState.Success -> {
                    Resource.Success(state.data)
                }
                is ApiState.Error -> {
                    Resource.Error(state.message?:UiText.DynamicString("Something went wrong"))
                }
                is ApiState.Loading -> {
                    Resource.Loading()
                }
            }
        }
    }
}