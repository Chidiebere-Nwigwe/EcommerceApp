package com.example.ecommerceapp.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSuccessPage(navController: NavController, finalPrice : String, address: String) {
    var address = address
    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Payment Success") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.navigate("home") }) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
//                    }
//                }
//            )
//        },
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(

                    onClick = { navController.navigate("notification?finalPrice=${finalPrice}&address=${address}") },
                    modifier = Modifier
                            .fillMaxWidth()
//                        .height(70.dp)
                        .padding(bottom = 30.dp)
                ) {
                    Text(text = "Continue")
                }

//                NavigationBar {
//                    val items = listOf(
//                        "home" to Icons.Default.Home,
//                        "shop" to Icons.Default.ShoppingCart,
//                        "coupon" to Icons.Default.Star,
//                        "wishlist" to Icons.Default.Favorite,
//                        "profile" to Icons.Default.Person
//                    )
//
//                    items.forEach { (route, icon) ->
//                        NavigationBarItem(
//                            icon = { Icon(icon, contentDescription = route) },
//                            label = { Text(route.replaceFirstChar { it.uppercase() }) },
//                            selected = false,
//                            onClick = { navController.navigate(route) }
//                        )
//                    }
//                }
            }
        }

    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success",
                tint = Color(0xFF4CAF50),
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Payment Successful!",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Thanks for your purchase.",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(finalPrice, fontWeight = FontWeight.Bold)
        }
    }
}
