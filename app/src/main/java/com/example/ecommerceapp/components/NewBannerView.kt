package com.example.ecommerceapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.R

@Composable
fun NewBannerView(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.group_93),
        contentDescription = "Banner Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier

            .fillMaxWidth()
            .height(250.dp)
    )
}