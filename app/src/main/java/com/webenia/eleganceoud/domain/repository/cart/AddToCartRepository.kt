package com.webenia.eleganceoud.domain.repository.cart

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.cart.add_to_cart.AddToCartResponse
import kotlinx.coroutines.flow.Flow

interface AddToCartRepository {
    fun addToCart(productId: Int, quantity: Int): Flow<Resource<AddToCartResponse>>
}