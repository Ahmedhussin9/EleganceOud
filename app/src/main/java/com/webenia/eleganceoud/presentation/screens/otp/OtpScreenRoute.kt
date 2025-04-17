package com.webenia.eleganceoud.presentation.screens.otp

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val ROUTE = "OTP"
fun NavController.navigateToOTPScreen() {
    navigate(ROUTE) {
        popUpTo(ROUTE) {
            inclusive = true
        }
        launchSingleTop
    }
}

fun NavGraphBuilder.displayOtpScreen(navHostController: NavHostController) {
    composable(ROUTE) {
        OtpScreenSetup(navController = navHostController)
    }
}