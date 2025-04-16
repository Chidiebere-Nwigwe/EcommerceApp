package com.example.ecommerceapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.pages.ProfilePage
import com.example.ecommerceapp.pages.ShopPage
import com.example.ecommerceapp.screen.AuthScreen
import com.example.ecommerceapp.screen.HomeScreen
import com.example.ecommerceapp.screen.LoginScreen
import com.example.ecommerceapp.screen.SignupScreen
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme
import com.example.ecommerceapp.viewmodel.AuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier = Modifier,authViewModel: AuthViewModel){
    // create nav controller
    val navController = rememberNavController()

    val isLoggedIn = Firebase.auth.currentUser!=null
    val firstPage = if(isLoggedIn) "home" else "auth"
    //create navHost
    Box(modifier = modifier.padding(horizontal = 5.dp)){
        NavHost(navController = navController, startDestination = firstPage, builder = {
            //composable for each page
            composable("auth"){
                AuthScreen(modifier, navController)
            }
            composable("login"){
                LoginScreen(modifier,authViewModel, navController)
            }
            composable("signup"){
                SignupScreen(modifier, authViewModel, navController)
            }
            composable("home"){
                HomeScreen(modifier, navController)
            }
            composable("shop") {
                ShopPage(
                    viewModel = viewModel(),
                    cartViewModel = viewModel(),
                    navController = navController
                )
            }


        })
    }
}