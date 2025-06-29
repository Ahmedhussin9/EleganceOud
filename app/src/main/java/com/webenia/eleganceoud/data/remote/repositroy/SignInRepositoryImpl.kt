package com.webenia.eleganceoud.data.remote.repositroy

import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.requests.login_request.SignInRequest
import com.webenia.eleganceoud.data.remote.response.signin.SignInResponse
import com.webenia.eleganceoud.domain.repository.SignInRepository
import com.webenia.eleganceoud.util.state.ApiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val webServices: WebServices
) : SignInRepository {
    override fun signInRequest(body: SignInRequest): Flow<Resource<SignInResponse>> {
        return toResultFlow {
            webServices.signIn(body)
        }.map { state ->
            when (state) {
                is ApiState.Success -> Resource.Success(state.data)
                is ApiState.Error -> Resource.Error(state.message!!)
                is ApiState.Loading -> Resource.Loading()
            }
        }
    }

}