package com.webenia.eleganceoud.presentation.screens.main

import androidx.annotation.DrawableRes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.webenia.eleganceoud.R
import com.webenia.eleganceoud.presentation.composables.ChipBottomNavigationBar
import com.webenia.eleganceoud.presentation.composables.TopBar
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.favorite.FavoriteScreenSetup
import com.webenia.eleganceoud.presentation.screens.cart.CartScreenSetup
import com.webenia.eleganceoud.presentation.screens.category.CategoryScreenSetup
import com.webenia.eleganceoud.presentation.screens.home.HomeScreenSetup

data class BottomNavItem(val label: String, @DrawableRes val iconRes: Int, val route: String)

val slideIn = slideInHorizontally { it } + fadeIn()
val slideOut = slideOutHorizontally { -it } + fadeOut()
val slideUp = slideInVertically(initialOffsetY = { it }) + fadeIn()

@Composable
fun MainScreenEntryPoint(
    navController: NavController
) {
    val navHostController = rememberNavController()
    MainScreenSetup(navHostController = navHostController, navController = navController)
}

@Composable
fun MainScreenSetup(
    navHostController: NavHostController,
    navController: NavController

) {
    val items = listOf(
        BottomNavItem("home", R.drawable.ic_home, AppDestination.Home.route),
        BottomNavItem("category", R.drawable.ic_category, AppDestination.Category.route),
        BottomNavItem("cart", R.drawable.ic_cart, AppDestination.Cart.route),
        BottomNavItem("favorite", R.drawable.ic_favorite, AppDestination.Favorite.route)
    )

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedIndex = items.indexOfFirst { it.route == currentRoute }.takeIf { it != -1 } ?: 0

    MainScreenContent(
        navController = navController,
        navHostController = navHostController,
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { index ->
            val selectedItem = items[index]
            if (selectedItem.route != currentRoute) {
                navHostController.navigate(selectedItem.route) {
                    popUpTo(items.first().route) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    )
}

@Composable
fun MainScreenContent(
    navHostController: NavHostController,
    navController: NavController,
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                onSearchClick = { },
                onBurgerClick = {
                    navController.navigate(
                        AppDestination.Settings.route
                    )
                }
            )
        },
        bottomBar = {
            ChipBottomNavigationBar(
                items = items,
                selectedIndex = selectedIndex,
                onItemSelected = onItemSelected
            )
        },
        modifier = Modifier.background(Color.White)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White)
        ) {
            NavHost(
                navController = navHostController,
                startDestination = "home",
                modifier = Modifier.fillMaxSize(),
            ) {
                composable("home") { HomeScreenSetup(
                    navController = navController
                ) }
                composable("category") {
                    CategoryScreenSetup(
                        navController = navController
                    )
                }
                composable("cart") { CartScreenSetup() }
                composable("favorite") { FavoriteScreenSetup() }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewMainScreen() {
    val items = listOf(
        BottomNavItem("home", R.drawable.ic_home, AppDestination.Home.route),
        BottomNavItem("category", R.drawable.ic_category, AppDestination.Category.route),
        BottomNavItem("cart", R.drawable.ic_cart, AppDestination.Cart.route),
        BottomNavItem("favorite", R.drawable.ic_favorite, AppDestination.Favorite.route)
    )
    var selectedIndex by remember { mutableIntStateOf(0) }
    MainScreenContent(
        navHostController = rememberNavController(),
        navController = rememberNavController(),
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { index -> selectedIndex = index })


}