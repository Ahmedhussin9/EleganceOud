package com.webenia.eleganceoud.domain.repository.auth

import com.webenia.eleganceoud.data.remote.response.auth.signin.LoginResponse
import com.webenia.eleganceoud.util.state.ApiState
import kotlinx.coroutines.flow.Flow

interface SignInRepository {
     fun signInRequest(
         email: String, password: String
    ): Flow<ApiState<LoginResponse>>
}