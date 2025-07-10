package com.webenia.eleganceoud.data.remote.response.auth.signout

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class SignOutResponse(
    val message: String,
)
