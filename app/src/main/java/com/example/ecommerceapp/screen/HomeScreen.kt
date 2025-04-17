package com.example.ecommerceapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.pages.*
import com.example.ecommerceapp.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    cartViewModel: CartViewModel = viewModel()
) {
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Shop", Icons.Default.ShoppingCart),
        NavItem("Coupon", Icons.Default.Home),
        NavItem("Wishlist", Icons.Default.Favorite),
        NavItem("Me", Icons.Default.Person)
    )

    var selected by remember { mutableStateOf(0) }

    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Welcome Back") },
//                actions = {
//                    IconButton(onClick = { navController.navigate("cart") }) {
//                        Icon(
//                            imageVector = Icons.Default.ShoppingCart,
//                            contentDescription = "Go to Cart"
//                        )
//                    }
//                }
//            )
//        },
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = index == selected,
                        onClick = { selected = index },
                        icon = {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.label,
                                tint = if (index == selected) Color(0xFF8D645B) else Color.Black
                            )
                        },
                        label = { Text(navItem.label) },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                            selectedIconColor = Color.Transparent,
                            unselectedIconColor = Color.Black,
                            selectedTextColor = Color(0xFF8D645B),
                            unselectedTextColor = Color.Black
                        )
                    )
                }
            }
        }
    ) { padding ->
        ContentScreen(
            modifier = modifier.padding(padding),
            selected = selected,
            navController = navController,
            cartViewModel = cartViewModel
        )
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selected: Int,
    navController: NavController,
    cartViewModel: CartViewModel
) {
    when (selected) {
        0 -> HomePage(modifier = modifier, navController = navController)
        1 -> ShopPage(navController = navController, cartViewModel = cartViewModel)
        2 -> CouponPage(modifier)
        3 -> WishlistPage(modifier)
        4 -> ProfilePage(modifier)
    }
}

data class NavItem(
    val label: String,
    val icon: ImageVector
)
