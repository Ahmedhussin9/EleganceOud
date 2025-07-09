package com.webenia.eleganceoud.data.remote

import com.webenia.eleganceoud.data.remote.requests.login_request.SignInRequest
import com.webenia.eleganceoud.data.remote.requests.resend_otp.ResendOtpRequest
import com.webenia.eleganceoud.data.remote.requests.resgister_request.RegisterRequest
import com.webenia.eleganceoud.data.remote.requests.submit_otp.SubmitOtpRequest
import com.webenia.eleganceoud.data.remote.response.home.our_products.ProductsResponse
import com.webenia.eleganceoud.data.remote.response.auth.otp.ResendOtpResponse
import com.webenia.eleganceoud.data.remote.response.auth.otp.SubmitOtpResponse
import com.webenia.eleganceoud.data.remote.response.auth.signout.SignOutResponse
import com.webenia.eleganceoud.data.remote.response.auth.signup.RegisterResponse
import com.webenia.eleganceoud.data.remote.response.home.best_sellings.HomeBestSellingResponse
import com.webenia.eleganceoud.data.remote.response.home.brands.HomeBrandsResponse
import com.webenia.eleganceoud.data.remote.response.home.category.CategoriesResponse
import com.webenia.eleganceoud.data.remote.response.home.latest_products.HomeLatestProductsResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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
    ): Response<ResendOtpResponse>

    @POST("api/client/verify-otp")
    @Headers("Accept: application/json")
    suspend fun submitOtp(
        @Body body: SubmitOtpRequest
    ): Response<SubmitOtpResponse>

    @GET("api/website/products/section")
    suspend fun getOurProducts(): Response<ProductsResponse>

    @GET("api/website")
    suspend fun getHomeCategories(): Response<CategoriesResponse>

    @GET("api/website/brands/section")
    suspend fun getHomeBrands(): Response<HomeBrandsResponse>

    @GET("api/website/best-selling/products")
    suspend fun getHomeBestSellingProducts(): Response<HomeBestSellingResponse>

    @GET("api/website/latest/products")
    suspend fun getHomeLatestProducts(): Response<HomeLatestProductsResponse>

    @POST("api/logout")
    @Headers("Accept: application/json")
    suspend fun signOut(
        @Header("Authorization") token: String
    ): Response<SignOutResponse>
}