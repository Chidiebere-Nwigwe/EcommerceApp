package com.example.ecommerceapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommerceapp.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentPage(navController: NavController, finalPrice: Double, coupon: String, address: String, cartViewModel: CartViewModel) {
    var selectedOption by remember { mutableStateOf("Visa") }
    var updatedFinalPrice = finalPrice
    if (coupon == "GARY2025" || coupon == "CHIDI2025" || coupon == "ANTHONY2025") {
        updatedFinalPrice -= 5
    }

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
                Column(Modifier.padding(16.dp)) {
                    Text("Payment Summary", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Final Price")
                        Text(
                            "$${"%.2f".format(finalPrice)}",
                            style = TextStyle(color = Color.DarkGray, textDecoration = TextDecoration.LineThrough),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Text("$${"%.2f".format(updatedFinalPrice)}", fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    val shape = RoundedCornerShape(12.dp)

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(65.dp)
                            .clip(shape)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(Color(0xFFF3AD9D), Color(0xFF8D645B))
                                )
                            )
                    ) {
                        Button(
                            onClick = {
                                navController.navigate("payment_success?updatedFinalPrice=${updatedFinalPrice}&address=${address}")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            modifier = Modifier.fillMaxSize(),
                            shape = shape,
                            contentPadding = PaddingValues()
                        ) {
                            Text("Pay Now", color = Color.White)
                        }
//                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    Spacer(modifier = Modifier.height(45.dp))
                }

            }
        }

    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val borderColor = Color(0xFF815D55)
            listOf("Visa", "PayPal", "Mastercard").forEach { method ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp) // Optional spacing between cards
                        .border(
                            width = 2.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (selectedOption == method)
                                MaterialTheme.colorScheme.primary.copy(0.1f)
                            else
                                MaterialTheme.colorScheme.surface
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
            }

        }
    }
}