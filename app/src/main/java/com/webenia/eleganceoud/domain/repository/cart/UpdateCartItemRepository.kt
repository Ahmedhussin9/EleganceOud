package com.webenia.eleganceoud.domain.repository.cart

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.cart.update_in_cart.UpdateCartItemResponse
import kotlinx.coroutines.flow.Flow

interface UpdateCartItemRepository {
    fun updateCartItem(quantity:Int,productId:Int):Flow<Resource<UpdateCartItemResponse>>
}