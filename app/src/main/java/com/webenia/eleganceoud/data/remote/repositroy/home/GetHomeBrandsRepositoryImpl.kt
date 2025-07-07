package com.webenia.eleganceoud.data.remote.repositroy.home

import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.response.home.brands.HomeBrandsResponse
import com.webenia.eleganceoud.domain.repository.home.GetHomeBrandsRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetHomeBrandsRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : GetHomeBrandsRepository {
    override fun getHomeBrands(): Flow<Resource<HomeBrandsResponse>> {
        return toResultFlow {
            webServices.getHomeBrands()
        }.map { state ->
            when (state) {
                is ApiState.Success -> {
                    Resource.Success(state.data)
                }

                is ApiState.Error -> {
                    Resource.Error(
                        state.message ?: UiText.DynamicString(
                            "Something went wrong"
                        )
                    )
                }

                is ApiState.Loading -> {
                    Resource.Loading()
                }
            }
        }
    }
}