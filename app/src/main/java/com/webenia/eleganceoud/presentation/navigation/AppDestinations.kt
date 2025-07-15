package com.webenia.eleganceoud.presentation.navigation

import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppDestination(
    val route: String,
    val icon: ImageVector? = null,
    val name: String? = null
) {
    object SignUp : AppDestination("sign_up")
    object Login : AppDestination("login")

    object ForgotPassword : AppDestination("forgot_password")
    object OnBoarding : AppDestination("on_boarding")
    object Splash : AppDestination("splash")
    object Main : AppDestination("main")
    object Home : AppDestination("home")
    object Category : AppDestination("category")
    object Cart : AppDestination("cart")
    object Favorite : AppDestination("favorite")
    object Settings : AppDestination(route = "settings")


    data class Otp(val email: String) : AppDestination("otp/{email}") {
        fun createRoute(email: String) = "otp/$email"
    }

    data class ProductDetails(val productId: Int) : AppDestination("product_details/{productId}") {
        fun createRoute(productId: Int) = "product_details/$productId"
    }
}
