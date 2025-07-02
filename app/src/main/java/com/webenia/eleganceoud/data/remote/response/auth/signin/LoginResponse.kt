package com.webenia.eleganceoud.data.remote.response.auth.signin

sealed class LoginResponse {
    data class Success(
        val token: String,
        val user: UserDto,
        val is_verified: Boolean
    ) : LoginResponse()

    data class Unverified(
        val message: String,
        val success: Boolean,
        val is_verified: Boolean
    ) : LoginResponse()
}

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val email_verified_at: String?,
    val role: String,
    val country_id: Int,
    val phone: String,
    val created_at: String,
    val updated_at: String,
    val otp: String?,
    val otp_expires_at: String?,
    val is_verified: Int
)
