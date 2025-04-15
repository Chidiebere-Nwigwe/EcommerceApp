package com.example.ecommerceapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.components.HeaderView
import com.example.ecommerceapp.components.NewBannerView

@Composable
fun HeaderAndBanner(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp) // Space above Welcome Back and icons
    ) {
        // Header Row with "Welcome Back", email, and icons
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "test@gmail.com",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart"
                )
            }
        }

        // Banner with text on left side
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.group_93),
                contentDescription = "Hero Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Text on the left-hand side of the image
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart) // Left-center alignment
                    .padding(start = 16.dp) // Padding from the left edge
            ) {
                Text(
                    text = "Spring Collection",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.White,
                        fontSize = 24.sp
                    )
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Lorem Ipsum is simply \ndummy text \n",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )
                Button(
                    onClick = { /* Handle CTA click */ },
                    shape = RoundedCornerShape(50),
                    contentPadding = PaddingValues(horizontal = 30.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),        // Background color
                        contentColor = Color.Black                 // Text/Icon color

                    )
                ) {
                    Text("Shop Now")
                }
            }
        }
    }
}