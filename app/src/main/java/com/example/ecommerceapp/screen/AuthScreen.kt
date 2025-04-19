package com.example.ecommerceapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerceapp.R

@Composable
fun AuthScreen(modifier: Modifier = Modifier, navController: NavHostController){
    Column(
        modifier  = modifier.fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.group_23),
            contentDescription = "Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text= "Start Shopping now",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            ),
            color = Color(0xFF8D645B)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text= "Best prices",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            ),
            color = Color(0xFF8D645B)
        )

        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFF3AD9D), Color(0xFF8D645B))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFF3AD9D), Color(0xFF8D645B))
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(2.dp) // Border thickness
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    TextButton(
                        onClick = {
                            navController.navigate("login")
                        },
                        modifier = Modifier.fillMaxSize(),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.textButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 22.sp,
//                            style = TextStyle.Default.copy(
//                                brush = Brush.horizontalGradient(
//                                    colors = listOf(Color(0xFFF3AD9D), Color(0xFF8D645B))
//                                )
//                            )
                        )
                    }
                }
            }
        }




        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFF3AD9D), Color(0xFF8D645B))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(2.dp) // Border thickness
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                TextButton (
                    onClick = {
                        navController.navigate("signup")
                    },
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Signup",
                        fontSize = 22.sp,
//                        style = TextStyle.Default.copy(
//                            brush = Brush.horizontalGradient(
//                                colors = listOf(Color(0xFFF3AD9D), Color(0xFF8D645B))
//                            )
//                        )
                    )
                }
            }
        }
    }
}