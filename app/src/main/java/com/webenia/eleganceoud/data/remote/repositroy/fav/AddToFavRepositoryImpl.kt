package com.webenia.eleganceoud.data.remote.repositroy.fav

import com.elegance_oud.util.UserUtil
import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.requests.add_to_fav.AddToFavRequest
import com.webenia.eleganceoud.data.remote.response.fav.AddToFavResponse
import com.webenia.eleganceoud.domain.repository.fav.AddToFavRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddToFavRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : AddToFavRepository {
    override fun addToFav(productId: Int): Flow<Resource<AddToFavResponse>> {
        val request = AddToFavRequest(
            product_id = productId
        )
        return toResultFlow {
            webServices.addToFav(
                token = "Bearer ${UserUtil.getToken()}",
                body = request,
            )
        }.map {
            when (it) {
                is ApiState.Success -> {
                    Resource.Success(it.data)
                }

                is ApiState.Error -> {
                    Resource.Error(it.message ?: UiText.DynamicString("Something went wrong."))
                }

                is ApiState.Loading -> {
                    Resource.Loading()
                }
            }
        }
    }
}