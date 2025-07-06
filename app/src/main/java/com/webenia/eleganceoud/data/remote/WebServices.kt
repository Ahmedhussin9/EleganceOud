package com.webenia.eleganceoud.data.remote

import com.webenia.eleganceoud.data.remote.requests.login_request.SignInRequest
import com.webenia.eleganceoud.data.remote.requests.resend_otp.ResendOtpRequest
import com.webenia.eleganceoud.data.remote.requests.resgister_request.RegisterRequest
import com.webenia.eleganceoud.data.remote.requests.submit_otp.SubmitOtpRequest
import com.webenia.eleganceoud.data.remote.response.auth.home.our_products.ProductsResponse
import com.webenia.eleganceoud.data.remote.response.auth.otp.ResendOtpResponse
import com.webenia.eleganceoud.data.remote.response.auth.otp.SubmitOtpResponse
import com.webenia.eleganceoud.data.remote.response.auth.signup.RegisterResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
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
    ): Response<ResponseBody>

    @POST("api/resend-otp")
    @Headers("Accept: application/json")
    suspend fun resendOtp(
        @Body body: ResendOtpRequest
    ):Response<ResendOtpResponse>

    @POST("api/client/verify-otp")
    @Headers("Accept: application/json")
    suspend fun submitOtp(
        @Body body: SubmitOtpRequest
    ):Response<SubmitOtpResponse>
    @GET("api/website/products/section")
    suspend fun getOurProducts():Response<ProductsResponse>
}