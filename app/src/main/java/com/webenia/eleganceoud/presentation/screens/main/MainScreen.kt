package com.webenia.eleganceoud.presentation.screens.main

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.webenia.eleganceoud.presentation.screens.FavoriteScreenSetup
import com.webenia.eleganceoud.presentation.screens.cart.CartScreenSetup
import com.webenia.eleganceoud.presentation.screens.category.CategoryScreenSetup
import com.webenia.eleganceoud.presentation.screens.home.HomeScreenSetup
import com.webenia.eleganceoud.ui.theme.Primary

data class BottomNavItem(val label: String, @DrawableRes val iconRes: Int, val route: String)

val slideIn = slideInHorizontally { it } + fadeIn()
val slideOut = slideOutHorizontally { -it } + fadeOut()
val slideUp = slideInVertically(initialOffsetY = { it }) + fadeIn()

@Composable
fun MainScreenEntryPoint() {
    val navController = rememberNavController()
    MainScreenSetup(navController = navController)
}

@Composable
fun MainScreenSetup(
    navController: NavHostController,
) {
    val items = listOf(
        BottomNavItem("home", R.drawable.ic_home, AppDestination.Home.route),
        BottomNavItem("category", R.drawable.ic_category, AppDestination.Category.route),
        BottomNavItem("cart", R.drawable.ic_cart, AppDestination.Cart.route),
        BottomNavItem("favorite", R.drawable.ic_favorite, AppDestination.Favorite.route)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedIndex = items.indexOfFirst { it.route == currentRoute }.takeIf { it != -1 } ?: 0

    MainScreenContent(
        navController = navController,
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { index ->
            val selectedItem = items[index]
            if (selectedItem.route != currentRoute) {
                navController.navigate(selectedItem.route) {
                    popUpTo(items.first().route) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreenContent(
    navController: NavHostController,
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                onSearchClick = { },
                onBurgerClick = { }
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
                navController = navController,
                startDestination = "home",
                modifier = Modifier.fillMaxSize(),
            ) {
                composable("home") { HomeScreenSetup() }
                composable("category") { CategoryScreenSetup() }
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
        navController = rememberNavController(),
        items = items,
        selectedIndex = selectedIndex,
        onItemSelected = { index -> selectedIndex = index })


}