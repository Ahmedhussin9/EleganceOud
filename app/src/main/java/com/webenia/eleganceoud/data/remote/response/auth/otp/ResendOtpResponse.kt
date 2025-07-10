package com.webenia.eleganceoud.data.remote.response.auth.otp

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class ResendOtpResponse(
    val message: String,
    val success: Boolean
)
