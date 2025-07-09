package com.webenia.eleganceoud.data.remote.repositroy.auth

import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.response.auth.signout.SignOutResponse
import com.webenia.eleganceoud.domain.repository.auth.SignOutRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SignOutRepositoryImpl @Inject constructor(
    val webServices: WebServices
) : SignOutRepository {
    override suspend fun signOut(token: String): Flow<Resource<SignOutResponse>> {
        return toResultFlow {
            webServices.signOut("Bearer $token")

        }.map {
            when (it) {
                is ApiState.Success -> Resource.Success(it.data)
                is ApiState.Error -> Resource.Error(
                    it.message ?: UiText.DynamicString("Try again later")
                )
                is ApiState.Loading -> Resource.Loading()
            }
        }
    }


}