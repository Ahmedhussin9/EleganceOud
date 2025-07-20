package com.webenia.eleganceoud.domain.repository.fav

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.fav.AddToFavResponse
import kotlinx.coroutines.flow.Flow

interface AddToFavRepository {
    fun addToFav(productId: Int): Flow<Resource<AddToFavResponse>>
}