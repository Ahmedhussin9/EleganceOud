package com.webenia.eleganceoud.data.remote

import com.webenia.eleganceoud.data.remote.requests.login_request.SignInRequest
import com.webenia.eleganceoud.data.remote.requests.resgister_request.RegisterRequest
import com.webenia.eleganceoud.data.remote.response.signin.SignInResponse
import com.webenia.eleganceoud.data.remote.response.signup.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface WebServices {
    @POST("api/clients/register")
    @Headers("Accept: application/json")
    suspend fun registerUser(@Body body: RegisterRequest): Response<RegisterResponse>

    @POST("api/login")
    @Headers("Accept: application/json")
    suspend fun signIn(
        @Body body: SignInRequest
    ): Response<SignInResponse>
}