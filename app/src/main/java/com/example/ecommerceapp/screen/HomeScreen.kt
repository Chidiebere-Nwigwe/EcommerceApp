package com.example.ecommerceapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
//import com.example.ecommerceapp.components.BannerView
import com.example.ecommerceapp.components.NewBannerView
import com.example.ecommerceapp.components.HeaderView
import com.example.ecommerceapp.pages.CouponPage
import com.example.ecommerceapp.pages.HomePage
import com.example.ecommerceapp.pages.ProfilePage
import com.example.ecommerceapp.pages.ShopPage
import com.example.ecommerceapp.pages.WishlistPage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController : NavController){
    val navItemList  = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Shop", Icons.Default.ShoppingCart),
        NavItem("Coupon", Icons.Default.Home),
        NavItem("Wishlist", Icons.Default.Favorite),
        NavItem("Me", Icons.Default.Person)
    )

    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFFF3AD9D), Color(0xFF8D645B))
    )

    var selected by remember{ mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar {

                navItemList.forEachIndexed{ index, navItem ->
                    NavigationBarItem(
                        selected = index==selected,
                        onClick = { selected = index },
                        icon = {

                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.label,
                                tint  = if (index == selected) Color(0xFF8D645B) else Color.Black
                            )
                        },

                        label = {
                            Text(text = navItem.label)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                            selectedIconColor =  Color.Transparent, // Color when selected
                            unselectedIconColor = Color.Black, // Color when not selected
                            selectedTextColor = Color(0xFF8D645B), // Text color when selected
                            unselectedTextColor = Color.Black // Text color when not selected
                        )
                    )
                }
            }
        }
    ){innerPadding ->
        ContentScreen(modifier = modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            selected = selected,
            navController = navController
        )
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selected: Int, navController: NavController){
    when(selected){
        0-> HomePage(modifier)
        1-> ShopPage(modifier)
        2-> CouponPage(modifier)
        3-> WishlistPage(modifier)
        4-> ProfilePage(modifier, navController)
    }

}

data class NavItem(
    val label : String,
    val icon : ImageVector
)
