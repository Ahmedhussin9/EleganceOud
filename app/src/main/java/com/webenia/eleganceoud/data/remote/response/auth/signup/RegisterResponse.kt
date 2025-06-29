package com.webenia.eleganceoud.data.remote.response.auth.signup

data class RegisterResponse(
    val status: Boolean,
    val message: String,
    val data: RegisterData
)

data class RegisterData(
    val token: Token,
    val user: User
)

data class Token(
    val name: String,
    val email: String,
    val role: String,
    val country_id: String,
    val phone: String,
    val updated_at: String,
    val created_at: String,
    val id: Int
)

data class User(
    val name: String,
    val email: String,
    val password: String,
    val role: String,
    val country_id: String,
    val phone: String,
    val otp: String,
    val otp_expires_at: String,
    val is_verified: Boolean
)
