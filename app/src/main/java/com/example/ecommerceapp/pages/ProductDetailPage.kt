package com.example.ecommerceapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.viewmodel.CartViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecommerceapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailPage(
    product: Product,
    navController: NavController,
    cartViewModel: CartViewModel = viewModel()
) {
    var quantity by remember { mutableStateOf(1) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
//    Column(){
//
//    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = product.title, maxLines = 1) },
                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
                    IconButton(onClick = { navController.navigate("shop") }) {

                    Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(R.drawable.group_94),
                contentDescription = "Logo"
            )
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = product.title,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = product.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Category: ${product.category}",
                style = MaterialTheme.typography.labelMedium
            )

            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text("Description", fontWeight = FontWeight.Bold)
            Text(product.description)

            Spacer(modifier = Modifier.height(24.dp))

            // 🔢 Quantity Selector
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                IconButton(onClick = {
                    if (quantity > 1) quantity--
                }) {
                    Icon(imageVector = Icons.Filled.Remove, contentDescription = "Decrease")
                }

                Text(quantity.toString(), style = MaterialTheme.typography.titleMedium)

                IconButton(onClick = {
                    quantity++
                }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Increase")
                }
            }

            // 🛒 Add to Cart
            Button(
                onClick = {
                    cartViewModel.addToCart(product, quantity)
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Added to cart!")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF27E900),
                    contentColor = Color.Black
                )
            ) {
                Text("Add to Cart")
            }
        }
    }
}
