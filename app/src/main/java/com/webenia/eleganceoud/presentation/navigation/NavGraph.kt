package com.webenia.eleganceoud.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.webenia.eleganceoud.presentation.screens.otp.displayOtpScreen
import com.webenia.eleganceoud.presentation.screens.signin.displaySignInScreen
import com.webenia.eleganceoud.presentation.screens.signup.displaySignUpScreen

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController = navController,startDestination ="sign_up"){
        displaySignInScreen(navController)
        displaySignUpScreen(navController)
        displayOtpScreen(navController)
    }
}