package com.example.ecommerceapp.model

import CartItem

data class OrderDetails(
    val items: List<CartItem>,
    val totalPrice: Double,  // Make sure to use totalPrice here
    val address: String
)


object OrderHolder {
    var currentOrder: OrderDetails? = null
}

