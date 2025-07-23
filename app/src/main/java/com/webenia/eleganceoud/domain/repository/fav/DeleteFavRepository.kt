package com.webenia.eleganceoud.domain.repository.fav

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.fav.AddToFavResponse
import com.webenia.eleganceoud.data.remote.response.fav.DeleteFavResponse
import kotlinx.coroutines.flow.Flow

interface DeleteFavRepository {
    suspend fun deleteFav(productId: Int): Flow<Resource<DeleteFavResponse>>
}