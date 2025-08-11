package com.webenia.eleganceoud.domain.repository.cart

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.cart.delete_item.DeleteCartItemResponse
import kotlinx.coroutines.flow.Flow

interface DeleteCartItemRepository {
    suspend fun deleteCartItem(productId: Int): Flow<Resource<DeleteCartItemResponse>>
}