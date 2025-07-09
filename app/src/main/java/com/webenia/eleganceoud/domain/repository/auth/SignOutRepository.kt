package com.webenia.eleganceoud.domain.repository.auth

import com.elegance_oud.util.state.Resource
import com.webenia.eleganceoud.data.remote.response.auth.signout.SignOutResponse
import kotlinx.coroutines.flow.Flow

interface SignOutRepository {
    suspend fun signOut(token: String): Flow<Resource<SignOutResponse>>
}