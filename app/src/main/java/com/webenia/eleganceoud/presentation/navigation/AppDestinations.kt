package com.webenia.eleganceoud.presentation.navigation

sealed class AppDestination(val route: String) {
    object SignUp : AppDestination("sign_up")
    object Login : AppDestination("login")
    object Home : AppDestination("home")
    object ForgotPassword : AppDestination("forgot_password")

    data class Otp(val email: String) : AppDestination("otp/{email}") {
        fun createRoute(email: String) = "otp/$email"
    }
}
