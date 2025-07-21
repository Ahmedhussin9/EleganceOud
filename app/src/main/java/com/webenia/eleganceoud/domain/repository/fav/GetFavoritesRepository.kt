package com.webenia.eleganceoud.domain.repository.fav

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.fav.GetFavoritesResponse
import kotlinx.coroutines.flow.Flow

interface GetFavoritesRepository {
    fun getFavorites(): Flow<Resource<GetFavoritesResponse>>
}