
package com.example.ecommerceapp.model

import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WishlistViewModel : ViewModel() {
    private val _wishlistItems = MutableStateFlow<List<Product>>(emptyList())
    val wishlistItems: StateFlow<List<Product>> = _wishlistItems

    fun addToWishlist(product: Product) {
        if (product !in _wishlistItems.value) {
            _wishlistItems.value = _wishlistItems.value + product
        }
    }

    fun removeFromWishlist(product: Product) {
        _wishlistItems.value = _wishlistItems.value - product
    }

    fun isInWishlist(product: Product): Boolean {
        return product in _wishlistItems.value
    }
}
