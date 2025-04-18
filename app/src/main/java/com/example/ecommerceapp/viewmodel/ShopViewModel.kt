package com.example.ecommerceapp.viewmodel

import android.content.Context
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

    // ❤️ Favorite product IDs
    private val _favorites = MutableStateFlow<Set<Int>>(emptySet())
    val favorites: StateFlow<Set<Int>> = _favorites

    init {
        fetchProducts()
    }

    // 🔍 Update search filter
    fun updateSearch(query: String) {
        _searchQuery.value = query
        applyFilters()
    }

    // 🧰 Update category filter
    fun updateCategory(category: String) {
        _selectedCategory.value = category
        applyFilters()
    }

    // ✅ Apply search + category filters
    private fun applyFilters() {
        val query = _searchQuery.value.lowercase()
        val category = _selectedCategory.value

        _filteredProducts.value = _allProducts.value.filter { product ->
            val matchesCategory = category == "All" || product.category.equals(category, ignoreCase = true)
            val matchesSearch = product.title.lowercase().contains(query)
            matchesCategory && matchesSearch
        }
    }

    // 🌐 Fetch from API
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

    // ❤️ Toggle favorite product
    fun toggleFavorite(productId: Int) {
        _favorites.value = _favorites.value.toMutableSet().apply {
            if (contains(productId)) remove(productId) else add(productId)
        }
    }

    // 💾 Save favorites to SharedPreferences
    fun saveFavoritesToPrefs(context: Context) {
        val prefs = context.getSharedPreferences("wishlist_prefs", Context.MODE_PRIVATE)
        prefs.edit().putStringSet("favorites", _favorites.value.map { it.toString() }.toSet()).apply()
    }

    // 📥 Load favorites from SharedPreferences
    fun loadFavoritesFromPrefs(context: Context) {
        val prefs = context.getSharedPreferences("wishlist_prefs", Context.MODE_PRIVATE)
        val savedSet = prefs.getStringSet("favorites", emptySet()) ?: emptySet()
        _favorites.value = savedSet.mapNotNull { it.toIntOrNull() }.toSet()
    }
}
