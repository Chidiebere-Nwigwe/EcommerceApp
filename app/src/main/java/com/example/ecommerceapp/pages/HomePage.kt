package com.example.ecommerceapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.components.HeaderView
import com.example.ecommerceapp.components.ProductCard
import com.example.ecommerceapp.viewmodel.CartViewModel
import com.example.ecommerceapp.viewmodel.ShopSecViewModel
import com.example.ecommerceapp.viewmodel.ShopViewModel
import kotlinx.coroutines.launch

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    selectedTab: MutableState<Int>
) {
    val viewModel: ShopViewModel = viewModel()
    val products by viewModel.filteredProducts.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val cartViewModel: CartViewModel = viewModel()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val categories = listOf("All", "men's clothing", "jewelery", "electronics", "women's clothing")
    val selectedCategory = remember { mutableStateOf("All") }

    // Enable vertical scrolling for the whole page
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header + Banner
        HeaderAndBanner(modifier, navController, selectedTab)

        // Categories title
        Text(
            text = "Categories",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            color = Color(0XFFCA9083)
        )

        // Category Chips
        LazyRow {
            items(categories.size) { index ->
                val category = categories[index]
                AssistChip(
                    onClick = {
                        selectedCategory.value = category
                        viewModel.updateCategory(category)
                    },
                    label = { Text(category) },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Category Products Horizontal List
        Text(
            text = "${selectedCategory.value} products",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )

        if (products.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(products) { product ->
                    ProductCard(
                        product = product,
                        isFavorite = favorites.contains(product.id),
                        onToggleFavorite = { viewModel.toggleFavorite(product.id) },
                        onClick = {
                            navController.navigate("product_detail/${product.id}")
                        },
                        onAddToCart = {
                            cartViewModel.addToCart(product)
                            coroutineScope.launch {
                                snackbarHostState.showSnackbar("${product.title} added to cart")
                            }
                        },
                        modifier = Modifier
                            .width(110.dp)
                            .height(200.dp)
                    )
                }
            }

        }

        // "For You" Section
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFF3AD9D),
                            Color(0xFF78574F)
                        )
                    )
                )
        ){
            Column(
                modifier = Modifier.padding(16.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "For You",
                        style = MaterialTheme.typography.titleMedium.copy
                            (fontWeight = FontWeight.Bold),
                        color = Color.White
                    )
                    OutlinedButton(onClick = { navController.navigate("shop") }) {
                        Text(
                            "See all",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.White
                        )
                    }
                }

                // 3-column Product Grid
                if (products.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 2000.dp),
                        contentPadding = PaddingValues(
                            start = 8.dp,
                            end = 8.dp,
                            top = 8.dp,
                            bottom = 32.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(products) { product ->
                            ProductCard(
                                product = product,
                                isFavorite = favorites.contains(product.id),
                                onToggleFavorite = { viewModel.toggleFavorite(product.id) },
                                onClick = {
                                    navController.navigate("product_detail/${product.id}")
                                },
                                onAddToCart = {
                                    cartViewModel.addToCart(product)
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("${product.title} added to cart")
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            )
                        }
                    }
                }
            }


        }

        Spacer(modifier = Modifier.height(32.dp)) // Bottom padding
    }
}