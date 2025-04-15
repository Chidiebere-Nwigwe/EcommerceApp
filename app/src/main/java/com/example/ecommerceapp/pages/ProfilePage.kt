package com.example.ecommerceapp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier, navController: NavController
) {
    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile Page",
            style = MaterialTheme.typography.headlineMedium
        )
        Button (
            onClick = {
                Firebase.auth.signOut()
                navController.navigate("auth"){
                    popUpTo("home"){
                        inclusive = true
                    }
                }
            }
        ) {
            Text(text = "Log out")
        }
    }
}