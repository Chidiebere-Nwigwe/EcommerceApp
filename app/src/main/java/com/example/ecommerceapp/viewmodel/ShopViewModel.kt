package com.example.ecommerceapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShopViewModel : ViewModel() {
    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts

    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow("All")

    init {
        fetchProducts()
    }

    // 🔍 Call this when user types in search bar
    fun updateSearch(query: String) {
        _searchQuery.value = query
        applyFilters()
    }

    // 🧰 Call this when a category chip is tapped
    fun updateCategory(category: String) {
        _selectedCategory.value = category
        applyFilters()
    }

    // ✅ Applies both search + category filters
    private fun applyFilters() {
        val query = _searchQuery.value.lowercase()
        val category = _selectedCategory.value

        _filteredProducts.value = _allProducts.value.filter { product ->
            val matchesCategory = category == "All" || product.category.equals(category, ignoreCase = true)
            val matchesSearch = product.title.lowercase().contains(query)
            matchesCategory && matchesSearch
        }
    }

    // 🌐 API Fetcher
    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val result = RetrofitInstance.api.getProducts()
                _allProducts.value = result
                _filteredProducts.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
