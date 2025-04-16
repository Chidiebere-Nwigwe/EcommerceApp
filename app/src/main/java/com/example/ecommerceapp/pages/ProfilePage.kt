
package com.example.ecommerceapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommerceapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun ProfilePage(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Your Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        // Profile Picture
        Image(
            painter = painterResource(id = R.drawable.group_69),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = 8.dp)
        )

        Text(
            text = "Guest",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Divider(color = Color(0xFF4B2E2B), thickness = 1.dp)

        // My Orders
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .clickable {
                    //navController.navigate("orders")
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "My Orders",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "My Orders",
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Go",
                tint = Color.Black
            )
        }

        Divider(color = Color(0xFF4B2E2B), thickness = 1.dp)

        // Logout Button aligned to the left and colored red
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Button(
                onClick = {
                    Firebase.auth.signOut()
                    navController.navigate("auth") {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Log out")
            }
        }
    }
}
