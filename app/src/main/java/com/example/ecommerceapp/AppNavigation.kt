package com.example.ecommerceapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.screen.AuthScreen
import com.example.ecommerceapp.screen.LoginScreen
import com.example.ecommerceapp.screen.SignupScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier){
    // create nav controller
    val navController = rememberNavController()

    //create navHost
    NavHost(navController = navController, startDestination = "auth", builder = {
        //composable for each page
        composable("auth"){
            AuthScreen(modifier, navController)
        }
        composable("login"){
            LoginScreen(modifier)
        }
        composable("signup"){
            SignupScreen(modifier)
        }
//        composable("home"){
//            HomePage(modifier, navController, authViewModel )
//        }


    } )
}