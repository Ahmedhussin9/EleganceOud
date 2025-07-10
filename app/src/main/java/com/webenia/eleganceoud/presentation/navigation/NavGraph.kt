package com.webenia.eleganceoud.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.elegance_oud.util.UserUtil
import com.webenia.eleganceoud.presentation.screens.favorite.FavoriteScreenSetup
import com.webenia.eleganceoud.presentation.screens.cart.CartScreenSetup
import com.webenia.eleganceoud.presentation.screens.category.CategoryScreenSetup
import com.webenia.eleganceoud.presentation.screens.home.HomeScreenSetup
import com.webenia.eleganceoud.presentation.screens.main.MainScreenEntryPoint
import com.webenia.eleganceoud.presentation.screens.on_boarding.OnBoardingScreenSetup
import com.webenia.eleganceoud.presentation.screens.otp.OtpScreenSetup
import com.webenia.eleganceoud.presentation.screens.product_details.ProductScreenSetup
import com.webenia.eleganceoud.presentation.screens.settings.SettingsScreenSetup
import com.webenia.eleganceoud.presentation.screens.signin.SignInScreenSetup
import com.webenia.eleganceoud.presentation.screens.signup.SignUpScreenSetup
import com.webenia.eleganceoud.presentation.screens.splash.SplashScreenSetup

@Composable
fun NavGraph(navController: NavHostController) {
    val slideIn = slideInHorizontally { it } + fadeIn()
    val slideOut = slideOutHorizontally { -it } + fadeOut()
    val slideUp = slideInVertically(initialOffsetY = { it }) + fadeIn()
    val slideDown = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    val fadeOnly = fadeIn().togetherWith(fadeOut())
    val scaleInOut =
        (scaleIn(initialScale = 0.8f) + fadeIn()).togetherWith(scaleOut(targetScale = 1.2f) + fadeOut())

    NavHost(navController = navController, startDestination = AppDestination.Splash.route,
        enterTransition = {
            slideIn
        },
        exitTransition = { slideOut },
        popEnterTransition = { slideUp },
        popExitTransition = { slideDown }) {
        composable(AppDestination.Splash.route) {
            SplashScreenSetup(onDone = {
                if (UserUtil.isFirstTime()) {
                    navController.navigate(AppDestination.OnBoarding.route)
                } else {
                    if (UserUtil.isLogin()) {
                        navController.navigate(AppDestination.Main.route)
                    } else {
                        navController.navigate(AppDestination.Login.route)
                    }
                }
            })
        }
        composable(AppDestination.OnBoarding.route) {
            OnBoardingScreenSetup(onDone = {

                UserUtil.saveFirstTime(false)
                navController.navigate(AppDestination.SignUp.route)
            })
        }
        composable(AppDestination.Main.route) {
            MainScreenEntryPoint(navController = navController)
        }
        composable(AppDestination.Category.route) {
            CategoryScreenSetup(navController = navController)
        }
        composable(AppDestination.Favorite.route) {
            FavoriteScreenSetup()
        }
        composable(AppDestination.Cart.route) {
            CartScreenSetup()
        }

        composable(AppDestination.SignUp.route) {
            SignUpScreenSetup(navController = navController)
        }

        composable(AppDestination.Login.route) {
            SignInScreenSetup(navController = navController)
        }

        composable(AppDestination.Home.route) {
            HomeScreenSetup()
        }
        composable(AppDestination.Settings.route) {
            SettingsScreenSetup(navController = navController)
        }

        composable(
            route = AppDestination.Otp("{email}").route,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            OtpScreenSetup(email = email, navController = navController)
        }
        composable(AppDestination.ProductDetails.route){
            ProductScreenSetup()

        }
    }

}