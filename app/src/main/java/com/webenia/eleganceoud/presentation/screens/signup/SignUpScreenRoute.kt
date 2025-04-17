package com.webenia.eleganceoud.presentation.screens.signup

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

private const val ROUTE = "sign_up"
fun NavController.navigateToSignUpScreen(){
    navigate(ROUTE){
        popUpTo(ROUTE){
            inclusive = true
        }
        launchSingleTop = true
    }
}
fun NavGraphBuilder.displaySignUpScreen(navController:NavHostController){
    composable(ROUTE, enterTransition ={
        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300))
    }, exitTransition ={
        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300))
    } ){
        SignUpScreenSetup(navController = navController)
    }

}