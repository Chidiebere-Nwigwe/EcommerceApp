
package com.example.ecommerceapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutPage(navController: NavController, cartViewModel: CartViewModel) {
    var address by remember { mutableStateOf("") }
    var coupon by remember { mutableStateOf("") }

    val cartItems by cartViewModel.cartItems.collectAsState()
    val subtotal = cartItems.sumOf { it.product.price * it.quantity }
    val shippingFee = 5.00
    val finalPrice = subtotal + shippingFee

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
        bottomBar = {
            val shape = RoundedCornerShape(12.dp)

            Column {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
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
                            navController.navigate("payment?finalPrice=${finalPrice}&coupon=${coupon}&address=${address}")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier.fillMaxSize(),
                        shape = shape,
                        contentPadding = PaddingValues()
                    ) {
                        Text("Continue to Checkout", color = Color.White)
                    }
                }

                // You can increases the space under button
                Spacer(modifier = Modifier.height(50.dp))
            }
        }

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
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF815D55),
                    unfocusedBorderColor = Color(0xFF815D55)
                )
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(12.dp)) {
                    Text("Items: ${cartItems.size}", fontWeight = FontWeight.Bold)
                    Text("${cartItems.sumOf { it.quantity }} x items")
                }
            }


            OutlinedTextField(
                value = coupon,
                onValueChange = { coupon = it },
                label = { Text("Apply Coupon") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF815D55),
                    unfocusedBorderColor = Color(0xFF815D55)
                )
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Payment Summary", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                RowItem("Sub-total", "$${"%.2f".format(subtotal)}")
                RowItem("Shipping Fee", "$${"%.2f".format(shippingFee)}")
                RowItem("Final Price", "$${"%.2f".format(finalPrice)}", highlight = true)
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
