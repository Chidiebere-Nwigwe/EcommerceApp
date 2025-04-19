
package com.example.ecommerceapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CouponPage(modifier: Modifier = Modifier, navController: NavController) {
    Scaffold(
        topBar = {
            // TopBar with Back Button
            CenterAlignedTopAppBar(
                title = { Text(text = "Coupon Page") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("cart") }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Row(
                    modifier = Modifier.padding(25.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(110.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xFF815D55),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(
                                color = Color(0xFF815D55)
                            )
                    ) {}

                    Box(
                        modifier = Modifier
                            .width(270.dp)
                            .height(110.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xFF815D55),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp), // Padding for the whole column inside the box
                            verticalArrangement = Arrangement.spacedBy(4.dp) // Control space between text elements
                        ) {
                            Text(
                                text = "ANTHONY2025",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Copy Coupon Code and Apply when Purchasing.",
                                fontSize = 10.sp,
                                color = Color(0xFF815D55)
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "31 December 2025",
                                fontSize = 10.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.padding(25.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(110.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xFF815D55),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(
                                color = Color(0xFF815D55)
                            )
                    ) {}

                    Box(
                        modifier = Modifier
                            .width(270.dp)
                            .height(110.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xFF815D55),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "CHIDI2025",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Copy Coupon Code and Apply when Purchasing.",
                                fontSize = 10.sp,
                                color = Color(0xFF815D55)
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "31 December 2025",
                                fontSize = 10.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.padding(25.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(110.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xFF815D55),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .background(
                                color = Color(0xFF815D55)
                            )
                    ) {}

                    Box(
                        modifier = Modifier
                            .width(270.dp)
                            .height(110.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                width = 2.dp,
                                color = Color(0xFF815D55),
                                shape = RoundedCornerShape(12.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "GARY2025",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Copy Coupon Code and Apply when Purchasing.",
                                fontSize = 10.sp,
                                color = Color(0xFF815D55)
                            )

                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "31 December 2025",
                                fontSize = 10.sp,
                                color = Color.DarkGray
                            )
                        }
                    }
                }
            }
        }
    )
}
