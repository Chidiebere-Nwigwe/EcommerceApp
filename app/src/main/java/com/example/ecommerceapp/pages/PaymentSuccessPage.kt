package com.example.ecommerceapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun PaymentSuccessPage(navController: NavController, finalPrice : String, address: String, cartViewModel: CartViewModel) {
    var address = address
    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                val shape = RoundedCornerShape(12.dp)

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
                            cartViewModel.clearCart() // Clear the cart first ✅,
                            navController.navigate("notification?finalPrice=${finalPrice}&address=${address}")
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier.fillMaxSize(),
                        shape = shape,
                        contentPadding = PaddingValues()
                    ) {
                        Text("Continue", color = Color.White)
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
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
