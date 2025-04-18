package com.example.ecommerceapp.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentPage(navController: NavController,  finalPrice: Double, coupon: String, address : String) {
    var selectedOption by remember { mutableStateOf("Visa") }
    var updatedFinalPrice = finalPrice;
    if(coupon=="GARY2025"){
        updatedFinalPrice -= 5;
    }
    var address = address;
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Choose Payment Option") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        },
        bottomBar = {
            Column {
                Column(Modifier.padding(16.dp)
                    ) {
                    Text("Payment Summary", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Final Price")
             //          Spacer(modifier = Modifier.weight(0.5f))
                        Text(
                            finalPrice.toString(),
                            style = TextStyle(
                                color = Color.DarkGray, // Set the color to dark gray
                                textDecoration = TextDecoration.LineThrough // Apply the strikethrough effect
                            ),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Text(updatedFinalPrice.toString(), fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(

//                        onClick = { navController.navigate("payment_success") },
                       onClick = { navController.navigate("payment_success?updatedFinalPrice=${updatedFinalPrice}&address=${address}") },

                        modifier = Modifier
                                .fillMaxWidth()
                            .height(70.dp)
                            .padding(bottom = 15.dp)
                    ) {
                        Text("Pay Now")
                    }
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
//                            label = { Text(route.capitalize()) },
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
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            listOf("Visa", "PayPal", "Mastercard").forEach { method ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedOption == method) MaterialTheme.colorScheme.primary.copy(0.1f) else MaterialTheme.colorScheme.surface
                    ),
                    onClick = { selectedOption = method }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(Icons.Default.CreditCard, method)
                        Text(method, Modifier.weight(1f))
                        RadioButton(
                            selected = selectedOption == method,
                            onClick = { selectedOption = method }
                        )
                    }
                }
            }
            OutlinedButton(
                onClick = { /* Add new card logic */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add new Card")
            }
        }
    }
}