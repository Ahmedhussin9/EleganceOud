package com.webenia.eleganceoud.data.remote.repositroy.fav

import com.elegance_oud.util.UserUtil
import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.response.fav.GetFavoritesResponse
import com.webenia.eleganceoud.domain.repository.fav.GetFavoritesRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoritesRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : GetFavoritesRepository {
    override fun getFavorites(): Flow<Resource<GetFavoritesResponse>> {
        return toResultFlow {
            webServices.getFavorites(
                token = "Bearer ${UserUtil.getToken()}"
            )
        }.map {
            when (it) {
                is ApiState.Error -> Resource.Error(
                    it.message ?: UiText.DynamicString(
                        "Something went wrong"
                    )
                )

                is ApiState.Loading -> Resource.Loading()
                is ApiState.Success -> Resource.Success(it.data)
            }
        }
    }
}