package com.webenia.eleganceoud.data.remote.repositroy.cart

import com.elegance_oud.util.UserUtil
import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.requests.update_cart_item.UpdateCartItemRequest
import com.webenia.eleganceoud.data.remote.response.cart.update_in_cart.UpdateCartItemResponse
import com.webenia.eleganceoud.domain.repository.cart.UpdateCartItemRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateCartItemRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : UpdateCartItemRepository {
    override fun updateCartItem(
        quantity: Int,
        productId: Int
    ): Flow<Resource<UpdateCartItemResponse>> {
        return toResultFlow {
            webServices.updateCartItem(
                token = "Bearer ${UserUtil.getToken()}",
                productId = productId,
                body = UpdateCartItemRequest(
                    quantity = quantity
                )
            )
        }.map { state ->
            when (state) {
                is ApiState.Success -> {
                    Resource.Success(state.data)
                }

                is ApiState.Error -> {
                    Resource.Error(
                        state.message ?: UiText.StringResource(R.string.something_went_wrong)
                    )
                }

                is ApiState.Loading -> {
                    Resource.Loading()

                }
            }
        }
    }
}