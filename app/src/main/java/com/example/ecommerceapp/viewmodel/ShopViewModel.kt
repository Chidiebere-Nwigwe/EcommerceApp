package com.example.ecommerceapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.api.RetrofitInstance
import com.example.ecommerceapp.datastore.FavoritesDataStore
import com.example.ecommerceapp.model.Product
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = FavoritesDataStore(application)

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts

    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow("All")

    private val _favorites = MutableStateFlow<Set<Int>>(emptySet())
    val favorites: StateFlow<Set<Int>> = _favorites

    init {
        fetchProducts()

        // Load persisted favorites
        viewModelScope.launch {
            dataStore.favorites.collect { storedFavorites ->
                _favorites.value = storedFavorites
            }
        }
    }

    fun updateSearch(query: String) {
        _searchQuery.value = query
        applyFilters()
    }

    fun updateCategory(category: String) {
        _selectedCategory.value = category
        applyFilters()
    }

    private fun applyFilters() {
        val query = _searchQuery.value.lowercase()
        val category = _selectedCategory.value

        _filteredProducts.value = _allProducts.value.filter { product ->
            val matchesCategory = category == "All" || product.category.equals(category, ignoreCase = true)
            val matchesSearch = product.title.lowercase().contains(query)
            matchesCategory && matchesSearch
        }
    }

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

    fun toggleFavorite(productId: Int) {
        val isNowFavorite = !_favorites.value.contains(productId)
        viewModelScope.launch {
            dataStore.updateFavorite(productId, isNowFavorite)
        }
    }
}
