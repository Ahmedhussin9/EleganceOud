package com.webenia.eleganceoud.domain.repository

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.requests.login_request.SignInRequest
import com.webenia.eleganceoud.data.remote.response.signin.SignInResponse
import kotlinx.coroutines.flow.Flow

interface SignInRepository {
     fun signInRequest(
        body: SignInRequest
    ): Flow<Resource<SignInResponse>>
}