package com.example.ecommerceapp.pages

import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.components.HeaderView
import com.example.ecommerceapp.components.NewBannerView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

@Composable
fun HeaderAndBanner(modifier: Modifier = Modifier, navController: NavController, selectedTab: MutableState<Int>) {

    var name by remember{ mutableStateOf("") }

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)
            .get().addOnCompleteListener(){
                name = it.result.get("email").toString()
            }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 35.dp) // Space above Welcome Back and icons
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.group_94),
            contentDescription = "Logo"
        )
        // Header Row with "Welcome Back", email, and icons
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Welcome Back",
                )
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Row {

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(onClick = { navController.navigate("cart") }) {
                    GradientIcon(
                        imageVector = Icons.Default.ShoppingBasket,
                        contentDescription = "Go to Cart",
                        gradientColors = listOf(
                            Color(0xFFF0AA9B),
                            Color(0xFF8A6259)
                        ),
                        modifier = Modifier.size(27.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
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
                    text = "Shop amazing deals with \nRose Bow \n",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                )
                Button(
                    onClick = { navController.navigate("shop")},

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

@Composable
fun GradientIcon(
    imageVector: ImageVector,
    contentDescription: String?,
    gradientColors: List<Color>,
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier
            .graphicsLayer(alpha = 0.99f)
            .drawWithCache {
                val brush = Brush.linearGradient(gradientColors)
                onDrawWithContent {
                    drawContent()
                    drawRect(
                        brush = brush,
                        blendMode = BlendMode.SrcAtop
                    )
                }
            },
        tint = Color.White
    )
}

