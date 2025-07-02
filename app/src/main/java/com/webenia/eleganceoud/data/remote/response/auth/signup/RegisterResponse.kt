package com.webenia.eleganceoud.data.remote.response.auth.signup

data class RegisterResponse(
    val message: String,
    val data: RegisterData,
    val success: Boolean,
    val token: String
)

data class RegisterData(
    val user: UserDto
)

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val role: String,
    val country_id: String,
    val otp: String,
    val otp_expires_at: String,
    val is_verified: Boolean,
    val updated_at: String,
    val created_at: String
)
