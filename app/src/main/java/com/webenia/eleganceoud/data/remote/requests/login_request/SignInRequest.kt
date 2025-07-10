package com.webenia.eleganceoud.data.remote.requests.login_request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SignInRequest(
    val email: String,
    val password: String
)
