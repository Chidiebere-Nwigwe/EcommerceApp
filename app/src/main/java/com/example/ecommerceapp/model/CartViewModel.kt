

package com.example.ecommerceapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CartView: ViewModel() {
    private val _selectedItems = mutableStateOf<List<CartItem>>(emptyList())
    val selectedItems: State<List<CartItem>> = _selectedItems

    fun addItem(item: CartItem) {
        _selectedItems.value = _selectedItems.value + item
    }

    fun removeItem(item: CartItem) {
        _selectedItems.value = _selectedItems.value - item
    }

    // Add more methods as necessary to modify the cart
}
