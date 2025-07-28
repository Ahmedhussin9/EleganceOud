package com.webenia.eleganceoud.data.remote.repositroy.cart

import com.elegance_oud.util.UserUtil
import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.requests.add_to_cart.AddToCartRequest
import com.webenia.eleganceoud.data.remote.response.cart.add_to_cart.AddToCartResponse
import com.webenia.eleganceoud.data.remote.response.fav.AddToFavResponse
import com.webenia.eleganceoud.domain.repository.cart.AddToCartRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddToCartRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : AddToCartRepository {
    override fun addToCart(productId: Int, quantity: Int): Flow<Resource<AddToCartResponse>> {
        return toResultFlow {
            webServices.addToCart(
                token = "Bearer ${UserUtil.getToken()}",
                body = AddToCartRequest(
                    product_id = productId,
                    quantity = quantity
                )
            )
        }.map {
            when (it) {
                is ApiState.Error -> Resource.Error(
                    it.message ?: UiText.DynamicString("Please try again later")
                )

                is ApiState.Loading -> Resource.Loading()
                is ApiState.Success -> Resource.Success(it.data)
            }
        }
    }
}