//package com.example.ecommerceapp
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.Modifier
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavType
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import androidx.navigation.navArgument
//import com.example.ecommerceapp.pages.CartPage
//import com.example.ecommerceapp.pages.CheckoutPage
//import com.example.ecommerceapp.pages.PaymentPage
//import com.example.ecommerceapp.pages.PaymentSuccessPage
//import com.example.ecommerceapp.pages.ProductDetailPage
//import com.example.ecommerceapp.pages.ShopPage
//import com.example.ecommerceapp.screen.AuthScreen
//import com.example.ecommerceapp.screen.HomeScreen
//import com.example.ecommerceapp.screen.LoginScreen
//import com.example.ecommerceapp.screen.SignupScreen
//import com.example.ecommerceapp.viewmodel.AuthViewModel
//import com.example.ecommerceapp.viewmodel.CartViewModel
//import com.example.ecommerceapp.viewmodel.ShopViewModel
//import com.google.firebase.Firebase
//import com.google.firebase.auth.auth
//
//@Composable
//fun AppNavigation(
//    modifier: Modifier = Modifier,
//    authViewModel: AuthViewModel
//) {
//    val navController = rememberNavController()
//    val isLoggedIn = Firebase.auth.currentUser != null
//    val firstPage = if (isLoggedIn) "home" else "auth"
//
//    // Shared CartViewModel
//    val cartViewModel: CartViewModel = viewModel()
//
//    NavHost(navController = navController, startDestination = firstPage) {
//
//        // 🔐 Auth Navigation
//        composable("auth") {
//            AuthScreen(modifier, navController)
//        }
//        composable("login") {
//            LoginScreen(modifier, authViewModel, navController)
//        }
//        composable("signup") {
//            SignupScreen(modifier, authViewModel, navController)
//        }
//
//        // ✅ Full navigation wrapper with bottom nav and top cart button
//        composable("home") {
//            HomeScreen(
//                modifier = modifier,
//                navController = navController,
//                cartViewModel = cartViewModel
//            )
//        }
//
//        // 🛍️ Product Detail Page (accessed from ShopPage)
//        composable(
//            route = "product_detail/{productId}",
//            arguments = listOf(navArgument("productId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val productId = backStackEntry.arguments?.getInt("productId")
//            val shopViewModel: ShopViewModel = viewModel()
//            val products = shopViewModel.filteredProducts.collectAsState().value
//            val product = products.firstOrNull { it.id == productId }
//
//            if (product != null) {
//                ProductDetailPage(
//                    product = product,
//                    navController = navController,
//                    cartViewModel = cartViewModel
//                )
//            }
//        }
//
//        // 🛒 Cart Page
//        composable("cart") {
//            CartPage(
//                cartViewModel = cartViewModel,
//                navController = navController
//            )
//        }
//        // ✅ Checkout Page
//        composable("checkout") {
//            CheckoutPage(navController = navController)
//        }
//        // Inside your NavHost composable in AppNavigation.kt
//        composable("payment") {
//            PaymentPage(navController)
//        }
//        composable("payment_success") {
//            PaymentSuccessPage(navController)
//        }
//
//
//    }
//}
package com.example.ecommerceapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
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
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    val navController = rememberNavController()
    val isLoggedIn = Firebase.auth.currentUser != null
    val firstPage = if (isLoggedIn) "home" else "auth"
    val selectedTab = rememberSaveable { mutableStateOf(0) }

    // Shared CartViewModel
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

        //Shop Navigation
        composable("shop"){
            ShopPage( shopViewModel, cartViewModel = cartViewModel, navController = navController)
        }
        //Coupon Navigation
        composable("coupon"){
            CouponPage(modifier, navController)
        }
        //Wishlist Navigation
        composable("wishlist"){
            WishlistPage(modifier, navController)
        }
        //Profile Navigation
        composable("me"){
            ProfilePage(modifier, navController)
        }
        //Notification Navigation
        composable("notification"){
            NotificationPage(navController, selectedTab)
        }

        // Product Detail Page
        composable(
            route = "product_detail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val shopViewModel: ShopViewModel = viewModel()
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


        // Checkout Page
        composable("checkout") {
            CheckoutPage(navController = navController)
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

