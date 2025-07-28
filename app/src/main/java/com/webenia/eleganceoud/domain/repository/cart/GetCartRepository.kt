package com.webenia.eleganceoud.domain.repository.cart

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.cart.get_cart.GetCartResponse
import kotlinx.coroutines.flow.Flow

interface GetCartRepository {
    fun getCartItems(): Flow<Resource<GetCartResponse>>
}