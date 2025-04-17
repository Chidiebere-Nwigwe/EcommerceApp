package com.example.ecommerceapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerceapp.screen.NavItem
import com.example.ecommerceapp.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartPage(
    cartViewModel: CartViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPrice by remember { derivedStateOf { cartViewModel.getTotalPrice() } }

    var selected by remember { mutableStateOf(0) }

    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home, "home"),
        NavItem("Shop", Icons.Default.ShoppingCart, "shop"),
        NavItem("Coupon", Icons.Default.CardGiftcard, "coupon"),
        NavItem("Wishlist", Icons.Default.Favorite, "wishlist"),
        NavItem("Me", Icons.Default.Person, "me")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cart") },
                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
                    IconButton(onClick = { navController.navigate("shop") }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Total", style = MaterialTheme.typography.titleMedium)
                    Text("$${"%.2f".format(totalPrice)}", style = MaterialTheme.typography.titleMedium)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { navController.navigate("checkout") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Checkout")
                }

                Spacer(modifier = Modifier.height(8.dp))

//                NavigationBar {
//                    NavigationBarItem(
//                        selected = false,
//                        onClick = { navController.navigate("home") },
//                        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
//                        label = { Text("Home") }
//                    )
//                    NavigationBarItem(
//                        selected = true,
//                        onClick = { navController.navigate("shop") },
//                        icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Shop") },
//                        label = { Text("Shop") }
//                    )
//                    NavigationBarItem(
//                        selected = false,
//                        onClick = { /* TODO */ },
//                        icon = { Icon(Icons.Default.Star, contentDescription = "Coupon") },
//                        label = { Text("Coupon") }
//                    )
//                    NavigationBarItem(
//                        selected = false,
//                        onClick = { /* TODO */ },
//                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Wishlist") },
//                        label = { Text("Wishlist") }
//                    )
//                    NavigationBarItem(
//                        selected = false,
//                        onClick = { /* TODO */ },
//                        icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
//                        label = { Text("Me") }
//                    )
//                }
                NavigationBar {
                    navItemList.forEachIndexed { index, navItem ->
                        NavigationBarItem(
                            selected = index == selected,
                            onClick = {
                                selected = index
                                navController.navigate(navItem.navName)
                            },
                            icon = {
                                Icon(
                                    imageVector = navItem.icon,
                                    contentDescription = navItem.label,
                                    tint = if (index == selected) Color(0xFF8D645B) else Color.Black
                                )
                            },
                            label = { Text(navItem.label) },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedIconColor = Color.Transparent,
                                unselectedIconColor = Color.Black,
                                selectedTextColor = Color(0xFF8D645B),
                                unselectedTextColor = Color.Black
                            )
                        )
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cartItems) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = true,
                            onCheckedChange = { /* Handle item selection logic */ }
                        )

                        Image(
                            painter = rememberAsyncImagePainter(item.product.image),
                            contentDescription = item.product.title,
                            modifier = Modifier.size(70.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.product.title, maxLines = 1, style = MaterialTheme.typography.bodyMedium)
                            Text("$${item.product.price}", color = MaterialTheme.colorScheme.primary)
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = { cartViewModel.decreaseQuantity(item.product) }) {
                                Icon(Icons.Default.Remove, contentDescription = "Decrease")
                            }

                            Text(item.quantity.toString())

                            IconButton(onClick = { cartViewModel.increaseQuantity(item.product) }) {
                                Icon(Icons.Default.Add, contentDescription = "Increase")
                            }
                        }
                    }
                }
            }
        }
    }
}