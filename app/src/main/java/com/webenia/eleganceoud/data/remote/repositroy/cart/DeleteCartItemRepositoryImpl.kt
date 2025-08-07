package com.webenia.eleganceoud.data.remote.repositroy.cart

import com.elegance_oud.util.UserUtil
import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.response.cart.delete_item.DeleteCartItemResponse
import com.webenia.eleganceoud.domain.repository.cart.DeleteCartItemRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeleteCartItemRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : DeleteCartItemRepository {
    override suspend fun deleteCartItem(productId: Int): Flow<Resource<DeleteCartItemResponse>> {
        return toResultFlow {
            webServices.deleteCartItem(
                token = "Bearer ${UserUtil.getToken()}",
                productId = productId
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

