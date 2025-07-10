package com.webenia.eleganceoud.data.remote.requests.resend_otp

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class ResendOtpRequest(
    val email: String,
)
