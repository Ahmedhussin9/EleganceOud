package com.webenia.eleganceoud.domain.model

// domain.model.User.kt
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val role: String,
    val countryId: Int,
    val isVerified: Boolean
)

