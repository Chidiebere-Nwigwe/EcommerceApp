package com.example.ecommerceapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ecommerceapp.pages.*
import com.example.ecommerceapp.screen.*
import com.example.ecommerceapp.viewmodel.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()
    val isLoggedIn = FirebaseAuth.getInstance().currentUser != null
    val firstPage = if (isLoggedIn) "home" else "auth"
    val selectedTab = rememberSaveable { mutableStateOf(0) }

    // Shared ViewModels
    val cartViewModel: CartViewModel = viewModel()
    val shopViewModel: ShopViewModel = viewModel()

    NavHost(navController = navController, startDestination = firstPage) {

        // Auth Navigation
        composable("auth") { AuthScreen(modifier, navController) }
        composable("login") { LoginScreen(modifier, authViewModel, navController) }
        composable("signup") { SignupScreen(modifier, authViewModel, navController) }

        // Home Navigation
        composable("home") {
            HomeScreen(
                modifier = Modifier,
                navController = navController,
                cartViewModel = cartViewModel,
                selectedTab = selectedTab
            )
        }

        // Shop Navigation
        composable("shop") {
            ShopPage(
                shopViewModel = shopViewModel,
                cartViewModel = cartViewModel,
                navController = navController
            )
        }

        // Product Detail Page
        composable(
            route = "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val products = shopViewModel.filteredProducts.collectAsState().value
            val product = products.firstOrNull { it.id == productId }

            product?.let {
                ProductDetailPage(
                    product = it,
                    navController = navController,
                    cartViewModel = cartViewModel
                )
            }
        }

        // Cart Page
        composable("cart") {
            CartPage(cartViewModel = cartViewModel, navController = navController)
        }

        // Checkout Page - Pass selected items from the CartViewModel
        composable("checkout") {
            val selectedItems = cartViewModel.selectedItems.collectAsState().value
            CheckoutPage(
                navController = navController,
                selectedItems = selectedItems // Pass selected items to CheckoutPage
            )
        }

        // Payment Page
        composable("payment") {
            PaymentPage(navController = navController)
        }

        // Payment Success Page
        composable("payment_success") {
            PaymentSuccessPage(navController = navController)
        }
    }
}
