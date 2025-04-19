package com.example.ecommerceapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.pages.GradientIcon

@Composable
fun ProductCard(
    product: Product,
    isFavorite: Boolean = false,
    onClick: () -> Unit,
    onToggleFavorite: () -> Unit = {},
    onAddToCart: () -> Unit = {},
    modifier: Modifier

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD9D9D9)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            // Product Image + Text wrapped in clickable
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClick() }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(product.image),
                    contentDescription = product.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()

                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(product.title, fontWeight = FontWeight.Bold, maxLines = 2)
                Text("$${product.price}", color = Color.Gray)
                Text("⭐ ${product.rating.rate} (${product.rating.count})", fontSize = 12.sp)

            }

            Spacer(modifier = Modifier.height(6.dp))

            //Favorite + Cart Icons
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onToggleFavorite) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Toggle Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
                IconButton(onClick = onAddToCart) {

                    GradientIcon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Go to Cart",
                        gradientColors = listOf(
                            Color(0xFFF0AA9B),
                            Color(0xFF8A6259)
                        ),
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}


