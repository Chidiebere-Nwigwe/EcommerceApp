package com.example.ecommerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class CartItem(val product: Product, var quantity: Int)

class CartViewModel : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    // Add item or increase quantity
    fun addToCart(product: Product, quantity: Int = 1) {
        val currentList = _cartItems.value.toMutableList()
        val index = currentList.indexOfFirst { it.product.id == product.id }

        if (index >= 0) {
            currentList[index] = currentList[index].copy(
                quantity = currentList[index].quantity + quantity
            )
        } else {
            currentList.add(CartItem(product, quantity))
        }

        _cartItems.value = currentList
    }

    // Decrease quantity or remove item
    fun decreaseQuantity(product: Product) {
        val currentList = _cartItems.value.toMutableList()
        val index = currentList.indexOfFirst { it.product.id == product.id }

        if (index >= 0) {
            val item = currentList[index]
            if (item.quantity > 1) {
                currentList[index] = item.copy(quantity = item.quantity - 1)
            } else {
                currentList.removeAt(index)
            }
        }

        _cartItems.value = currentList
    }

    // Increase quantity
    fun increaseQuantity(product: Product) {
        val currentList = _cartItems.value.toMutableList()
        val index = currentList.indexOfFirst { it.product.id == product.id }

        if (index >= 0) {
            currentList[index] = currentList[index].copy(
                quantity = currentList[index].quantity + 1
            )
        }

        _cartItems.value = currentList
    }

    // Remove item completely
    fun removeFromCart(product: Product) {
        _cartItems.value = _cartItems.value.filterNot {
            it.product.id == product.id
        }
    }

    // Total price calculation
    fun getTotalPrice(): Double {
        return _cartItems.value.sumOf { it.product.price * it.quantity }
    }
}