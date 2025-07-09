package com.webenia.eleganceoud.data.remote.repositroy.auth

import android.util.Log
import com.google.gson.Gson
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.data.remote.WebServices
import com.webenia.eleganceoud.data.remote.requests.login_request.SignInRequest
import com.webenia.eleganceoud.data.remote.response.auth.signin.LoginResponse
import com.webenia.eleganceoud.data.remote.response.auth.signin.UserDto
import com.webenia.eleganceoud.domain.repository.auth.SignInRepository
import com.webenia.eleganceoud.util.state.ApiState
import com.webenia.eleganceoud.util.state.UiText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject
class SignInRepositoryImpl @Inject constructor(
    private val webServices: WebServices
) : SignInRepository {

    override fun signInRequest(email: String, password: String): Flow<ApiState<LoginResponse>> {
        return flow {
            emit(ApiState.Loading())

            try {
                val response = webServices.signIn(
                    SignInRequest(
                        email = email,
                        password = password
                    )
                )

                if (response.isSuccessful) {
                    val body = response.body()?.string()
                    val json = JSONObject(body ?: "")
                    val gson = Gson()

                    if (json.has("token")) {
                        val user = gson.fromJson(json.getJSONObject("user").toString(), UserDto::class.java)
                        val token = json.getString("token")
                        val is_verified = json.getBoolean("is_verified")

                        emit(
                            ApiState.Success(
                                LoginResponse.Success(token = token, user = user,is_verified = is_verified)
                            )
                        )
                    } else {
                        emit(
                            ApiState.Success(
                                LoginResponse.Unverified(
                                    message = json.optString("message", "Unverified"),
                                    success = json.optBoolean("success", false),
                                    is_verified = json.optBoolean("is_verified", false)
                                )
                            )
                        )
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorJson = JSONObject(errorBody ?: "")
                    val errorMessage = errorJson.optString("message", "Something went wrong")

                    emit(ApiState.Error(UiText.DynamicString(errorMessage)))
                    Log.e("SignInError", "HTTP Error: $errorBody")
                }
            } catch (e: Exception) {
                Log.e("SignInException", e.message.toString())
                emit(ApiState.Error(UiText.StringResource(R.string.something_went_wrong)))
            }
        }
    }
}
