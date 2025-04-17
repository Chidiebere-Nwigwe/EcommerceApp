
package com.example.ecommerceapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

//import com.example.ecommerceapp.components.BannerView
import com.example.ecommerceapp.components.NewBannerView
import com.example.ecommerceapp.components.HeaderView

@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController, selectedTab: MutableState<Int>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header: Logo + Icons And Banner Image
        HeaderAndBanner(modifier, navController, selectedTab)


        Spacer(modifier = Modifier.height(16.dp))

        // Categories title
        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Category tabs (All, Men, Women, etc.)
        val categories = listOf("All", "Men", "Women", "Electronics", "Jewelry")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                Text(
                    text = category,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (category == "All") FontWeight.Bold else FontWeight.Normal
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Horizontal product list
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(5) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.width(100.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        // Placeholder for product image
                    }
                    Text("Lorem", fontWeight = FontWeight.SemiBold)
                    Text("$0.00")
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Add to cart"
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "For You" section
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("For You", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            Text("See all", style = MaterialTheme.typography.labelMedium)
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Placeholder for "For You" list
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(3) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
            }
        }
    }
}
