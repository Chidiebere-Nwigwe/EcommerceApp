// FavoritesDataStore.kt
package com.example.ecommerceapp.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_preferences")

class FavoritesDataStore(private val context: Context) {

    private val FAVORITES_KEY = stringSetPreferencesKey("favorites")

    val favorites: Flow<Set<Int>> = context.dataStore.data
        .map { preferences ->
            preferences[FAVORITES_KEY]?.mapNotNull { it.toIntOrNull() }?.toSet() ?: emptySet()
        }

    suspend fun updateFavorite(productId: Int, isFavorite: Boolean) {
        context.dataStore.edit { preferences ->
            val current = preferences[FAVORITES_KEY]?.toMutableSet() ?: mutableSetOf()
            if (isFavorite) {
                current.add(productId.toString())
            } else {
                current.remove(productId.toString())
            }
            preferences[FAVORITES_KEY] = current
        }
    }
}
