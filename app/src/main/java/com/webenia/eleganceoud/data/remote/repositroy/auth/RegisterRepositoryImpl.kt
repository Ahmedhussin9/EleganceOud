package com.webenia.eleganceoud.data.remote.repositroy.auth

import com.elegance_oud.util.state.Resource
import com.elegance_oud.util.toResultFlow
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.requests.resgister_request.RegisterRequest
import com.webenia.eleganceoud.data.remote.response.auth.signup.RegisterResponse
import com.webenia.eleganceoud.domain.repository.RegisterRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val api: WebServices
) : RegisterRepository {
    override fun registerUser(body: RegisterRequest): Flow<Resource<RegisterResponse>> {
        return toResultFlow {
            api.registerUser(body)
        }.map { state ->
            when (state) {
                is ApiState.Success -> Resource.Success(state.data)
                is ApiState.Error -> Resource.Error(state.message?: UiText.DynamicString("Try again later"))
                is ApiState.Loading -> Resource.Loading()
            }
        }
    }
}