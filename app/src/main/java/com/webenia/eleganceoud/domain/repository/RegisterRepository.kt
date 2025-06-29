package com.webenia.eleganceoud.domain.repository

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.requests.resgister_request.RegisterRequest
import com.webenia.eleganceoud.data.remote.response.auth.signup.RegisterResponse
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
     fun registerUser(body: RegisterRequest): Flow<Resource<RegisterResponse>>
}