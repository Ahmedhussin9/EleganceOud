package com.webenia.eleganceoud.data.remote.response.auth.signin

data class SignInResponse (
    val token: String,
    val user: User
)
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val emailVerifiedAt: String?,
    val role: String,
    val countryId: Int,
    val phone: String,
    val createdAt: String,
    val updatedAt: String,
    val otp: String,
    val otpExpiresAt: String?, // null is allowed
    val isVerified: Int // the API sends 0/1
)
