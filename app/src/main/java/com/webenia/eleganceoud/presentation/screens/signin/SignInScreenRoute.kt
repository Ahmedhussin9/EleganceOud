package com.webenia.eleganceoud.presentation.screens.signin

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val ROUTE = "sign_in"

fun NavController.navigateToSignInScreen() {
    navigate(ROUTE) {
        popUpTo(ROUTE) {
            inclusive = true
        }
        launchSingleTop = true
    }
}

fun NavGraphBuilder.displaySignInScreen(navHostController: NavHostController) {
    composable(ROUTE, enterTransition = {
        slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Up,
            animationSpec = tween(300)
        )

    }, exitTransition = {
        slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Down,
            animationSpec = tween(300)
        )
    }) {
        SignInScreenSetup(navController = navHostController)
    }
}