package com.webenia.eleganceoud.data.remote.requests.resgister_request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String,
    val country_id: String,
    val phone: String,
    val role:String
)
