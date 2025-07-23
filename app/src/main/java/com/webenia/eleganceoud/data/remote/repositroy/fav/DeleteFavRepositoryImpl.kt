package com.webenia.eleganceoud.data.remote.repositroy.fav

import com.elegance_oud.util.UserUtil
import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.response.fav.DeleteFavResponse
import com.webenia.eleganceoud.domain.repository.fav.DeleteFavRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeleteFavRepositoryImpl @Inject constructor(
    private val webServices: WebServices
) : DeleteFavRepository {
    override suspend fun deleteFav(productId: Int): Flow<Resource<DeleteFavResponse>> {
        return toResultFlow {
            webServices.deleteFav(
                token = "Bearer ${UserUtil.getToken()}",
                productId = productId
            )
        }.map {
            when (it) {
                is ApiState.Success -> {
                    Resource.Success(it.data)
                }

                is ApiState.Error -> {
                    Resource.Error(it.message?:UiText.DynamicString("Something went wrong"))
                }

                is ApiState.Loading -> {
                    Resource.Loading()

                }
            }
        }
    }
}