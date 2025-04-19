package com.example.ecommerceapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
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

        // Shop Page
        composable("shop") {
            ShopPage(shopViewModel, cartViewModel = cartViewModel, navController = navController)
        }

        // Product Detail Page
        composable(
            route = "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val product = shopViewModel.filteredProducts.collectAsState().value.firstOrNull { it.id == productId }
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

        // Checkout Page
        composable("checkout") {
            CheckoutPage(cartViewModel = cartViewModel, navController = navController)
        }

        // Payment Page
        composable(
            route = "payment?finalPrice={finalPrice}&coupon={coupon}&address={address}",
            arguments = listOf(
                navArgument("finalPrice") { type = NavType.StringType },
                navArgument("coupon") { type = NavType.StringType },
                navArgument("address") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val finalPriceString = backStackEntry.arguments?.getString("finalPrice") ?: "0.0"
            val coupon = backStackEntry.arguments?.getString("coupon") ?: ""
            val address = backStackEntry.arguments?.getString("address") ?: ""

            val finalPrice = finalPriceString.toDoubleOrNull() ?: 0.0 // Handle invalid price gracefully

            PaymentPage(
                navController = navController,
                finalPrice = finalPrice,
                coupon = coupon,
                address = address,
                cartViewModel = cartViewModel
            )
        }

        // Payment Success Page
        composable(
            route = "payment_success?updatedFinalPrice={updatedFinalPrice}&address={address}",
            arguments = listOf(
                navArgument("updatedFinalPrice") { type = NavType.StringType },
                navArgument("address") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val finalPriceString = backStackEntry.arguments?.getString("updatedFinalPrice") ?: "0.0"
            val address = backStackEntry.arguments?.getString("address") ?: ""

            PaymentSuccessPage(
                navController = navController,
                finalPrice = finalPriceString,
                address = address,
                cartViewModel = cartViewModel
            )
        }

        // Other Pages
        composable("coupon") { CouponPage(modifier, navController) }
        composable("wishlist") { WishlistPage(modifier, navController) }
        composable("me") { ProfilePage(modifier, navController) }

        // Notification Page
        composable("notification?finalPrice={finalPrice}&address={address}",
            arguments = listOf(
                navArgument("finalPrice") { type = NavType.StringType },
                navArgument("address") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val finalPrice = backStackEntry.arguments?.getString("finalPrice")?.toDoubleOrNull() ?: 0.0
            val address = backStackEntry.arguments?.getString("address") ?: ""

            NotificationPage(
                navController = navController,
                selectedTab = selectedTab,
                finalPrice = finalPrice,
                address = address
            )
        }
    }
}