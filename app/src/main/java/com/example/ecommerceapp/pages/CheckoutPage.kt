package com.example.ecommerceapp.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutPage(navController: NavController) {
    var address by remember { mutableStateOf("") }
    var coupon by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Checkout", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
//        bottomBar = {
//            NavigationBar {
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { navController.navigate("home") },
//                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
//                    label = { Text("Home") }
//                )
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { navController.navigate("shop") },
//                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shop") },
//                    label = { Text("Shop") }
//                )
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { navController.navigate("coupon") },
//                    icon = { Icon(Icons.Default.Star, contentDescription = "Coupon") },
//                    label = { Text("Coupon") }
//                )
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { navController.navigate("wishlist") },
//                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Wishlist") },
//                    label = { Text("Wishlist") }
//                )
//                NavigationBarItem(
//                    selected = false,
//                    onClick = { navController.navigate("profile") },
//                    icon = { Icon(Icons.Default.Person, contentDescription = "Me") },
//                    label = { Text("Me") }
//                )
//            }
//        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Choose Address") },
                modifier = Modifier.fillMaxWidth()
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(12.dp)) {
                    Text("LOREM IPSUM", fontWeight = FontWeight.Bold)
                    Text("0 x $0.00")
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Choose Shipping")
                TextButton(onClick = { /* edit logic */ }) {
                    Text("Edit")
                }
            }

            OutlinedTextField(
                value = coupon,
                onValueChange = { coupon = it },
                label = { Text("Apply Coupon") },
                modifier = Modifier.fillMaxWidth()
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Payment Summary", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                RowItem("Sub-total", "$0.00")
                RowItem("Shipping Fee", "$0.00")
                RowItem("Final Price", "$0.00", highlight = true)
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("payment") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Continue to Checkout")
            }

        }
    }
}

@Composable
fun RowItem(label: String, value: String, highlight: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label)
        Text(
            value,
            color = if (highlight) MaterialTheme.colorScheme.primary else Color.Unspecified,
            fontWeight = if (highlight) FontWeight.Bold else FontWeight.Normal
        )
    }
}
