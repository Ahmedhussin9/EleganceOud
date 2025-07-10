package com.webenia.eleganceoud.data.remote.requests.submit_otp

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class SubmitOtpRequest(
    val email: String,
    val otp: String
)

