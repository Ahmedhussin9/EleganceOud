package com.webenia.eleganceoud.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.webenia.eleganceoud.presentation.screens.home.HomeScreenSetup
import com.webenia.eleganceoud.presentation.screens.otp.OtpScreenSetup
import com.webenia.eleganceoud.presentation.screens.signin.SignInScreenSetup
import com.webenia.eleganceoud.presentation.screens.signup.SignUpScreenSetup

@Composable
fun NavGraph(navController: NavHostController) {
    val slideIn = slideInHorizontally { it } + fadeIn()
    val slideOut = slideOutHorizontally { -it } + fadeOut()
    val slideUp = slideInVertically(initialOffsetY = { it }) + fadeIn()
    val slideDown = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    val fadeOnly = fadeIn().togetherWith(fadeOut())
    val scaleInOut =
        (scaleIn(initialScale = 0.8f) + fadeIn()).togetherWith(scaleOut(targetScale = 1.2f) + fadeOut())

    NavHost(navController = navController, startDestination = AppDestination.SignUp.route,
        enterTransition = {
            slideIn
        },
        exitTransition = { slideOut },
        popEnterTransition = { slideUp },
        popExitTransition = { slideDown }) {
        composable(AppDestination.SignUp.route) {
            SignUpScreenSetup(navController = navController)
        }

        composable(AppDestination.Login.route) {
            SignInScreenSetup(navController = navController)
        }

        composable(AppDestination.Home.route) {
            HomeScreenSetup()
        }

        composable(
            route = AppDestination.Otp("{email}").route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            OtpScreenSetup(email = email, navController = navController)
        }
    }

}