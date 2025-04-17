package com.example.ecommerceapp.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.components.ProductCard
import com.example.ecommerceapp.viewmodel.CartViewModel
import com.example.ecommerceapp.viewmodel.ShopViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopPage(
    viewModel: ShopViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel(),
    navController: NavController
) {
    val products by viewModel.filteredProducts.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    var searchText by remember { mutableStateOf("") }

    // Snackbar + coroutine
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Shop") },
                actions = {
                    IconButton(onClick = { navController.navigate("cart") }) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // 🔍 Search Bar
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    viewModel.updateSearch(it)
                },
                label = { Text("Search products...") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🧰 Category Filter
            val categories = listOf("All", "men's clothing", "jewelery", "electronics", "women's clothing")
            LazyRow {
                items(categories.size) { index ->
                    val category = categories[index]
                    AssistChip(
                        onClick = { viewModel.updateCategory(category) },
                        label = { Text(category) },
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🧱 Product Grid (2 columns)
            if (products.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
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
                            }
                        )
                    }
                }
            }
        }
    }
}
