package com.example.ecommerceapp.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.viewmodel.CartItem

// Data class to represent a CartItem
//data class CartItem(
//    val id: Int,
//    val name: String,
//    val price: Double,
//    val quantity: Int
//)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutPage(navController: NavController, selectedItems: List<CartItem>) {
    var address by remember { mutableStateOf("") }
    var coupon by remember { mutableStateOf("") }

    // Calculate Subtotal, Shipping Fee, and Final Price
    val subtotal = selectedItems.sumOf { it.product.price * it.quantity }
    val shippingFee = 5.0 // This can be dynamic based on address or shipping method
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
                modifier = Modifier.fillMaxWidth()
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(12.dp)) {
                    Text("Selected Items", fontWeight = FontWeight.Bold)
                    selectedItems.forEach { item ->
                        Text("${item.product.title} x${item.quantity} - $${"%.2f".format(item.product.price * item.quantity)}")
                    }
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
                RowItem("Sub-total", "$${"%.2f".format(subtotal)}")
                RowItem("Shipping Fee", "$${"%.2f".format(shippingFee)}")
                RowItem("Final Price", "$${"%.2f".format(finalPrice)}", highlight = true)
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
