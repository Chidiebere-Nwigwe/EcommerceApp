package com.example.ecommerceapp.api

import com.example.ecommerceapp.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}
