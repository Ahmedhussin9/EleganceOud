package com.webenia.eleganceoud.data.remote.response.auth.otp

data class SubmitOtpResponse(
    val status: Boolean,
    val message: String,
    val data: VerifyOtpData
)
data class VerifyOtpData(
    val user: User,
    val token: String
)
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val email_verified_at: String?,
    val role: String,
    val country_id: Int,
    val phone: String,
    val created_at: String,
    val updated_at: String,
    val otp: String?, // nullable in this case
    val otp_expires_at: String?, // nullable
    val is_verified: Boolean
)
