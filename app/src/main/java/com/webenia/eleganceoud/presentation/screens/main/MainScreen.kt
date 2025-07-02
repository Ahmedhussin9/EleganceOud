package com.webenia.eleganceoud.presentation.screens.main

import androidx.compose.foundation.layout.Box
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
import com.webenia.eleganceoud.presentation.navigation.AppDestination
import com.webenia.eleganceoud.presentation.screens.FavoriteScreenSetup
import com.webenia.eleganceoud.presentation.screens.cart.CartScreenSetup
import com.webenia.eleganceoud.presentation.screens.category.CategoryScreenSetup
import com.webenia.eleganceoud.presentation.screens.home.HomeScreenSetup
import com.webenia.eleganceoud.ui.theme.Primary

data class BottomNavItem(val label: String, val icon: ImageVector, val route: String)
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
        BottomNavItem("home", Icons.Default.Home, AppDestination.Home.route),
        BottomNavItem("category", Icons.Default.List, AppDestination.Category.route),
        BottomNavItem("cart", Icons.Default.ShoppingCart, AppDestination.Cart.route),
        BottomNavItem("favorite", Icons.Default.Favorite, AppDestination.Favorite.route)
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


@Composable
fun MainScreenContent(
    navController: NavHostController,
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Scaffold(
        bottomBar = {
            ChipBottomNavigationBar(
                items = items,
                selectedIndex = selectedIndex,
                onItemSelected = onItemSelected
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreenSetup() }
            composable("category") { CategoryScreenSetup() }
            composable("cart") { CartScreenSetup() }
            composable("favorite") { FavoriteScreenSetup() }
        }
    }
}

@Composable
fun ChipBottomNavigationBar(
    items: List<BottomNavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
//                    Text(text = item.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Primary // chip-like background
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewMainScreen() {
    val items = listOf(
        BottomNavItem(icon = Icons.Default.Home, label = "Home", route = AppDestination.Home.route),
        BottomNavItem(
            icon = Icons.Default.List,
            label = "Category",
            route = AppDestination.Category.route
        ),
        BottomNavItem(
            icon = Icons.Default.ShoppingCart,
            label = "Cart",
            route = AppDestination.Cart.route
        ),
        BottomNavItem(
            icon = Icons.Default.Favorite,
            label = "Favorite",
            route = AppDestination.Favorite.route
        )
    )
    var selectedIndex by remember { mutableIntStateOf(0) }


}