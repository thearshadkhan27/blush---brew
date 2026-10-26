package com.example.ui

import androidx.lifecycle.ViewModel
import com.example.data.MenuItem
import com.example.data.MenuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CartItem(
    val item: MenuItem,
    val quantity: Int
)

class CafeViewModel : ViewModel() {
    // Cart: Map of itemId to quantity
    private val _cart = MutableStateFlow<Map<String, Int>>(emptyMap())
    val cart: StateFlow<Map<String, Int>> = _cart.asStateFlow()

    // Loyalty visits count (starts at 6 as in user design)
    private val _visits = MutableStateFlow(6)
    val visits: StateFlow<Int> = _visits.asStateFlow()

    // Bill Dialog visibility
    private val _showBillDialog = MutableStateFlow(false)
    val showBillDialog: StateFlow<Boolean> = _showBillDialog.asStateFlow()

    // Celebration modal when a stamp is awarded
    private val _showStampCelebration = MutableStateFlow(false)
    val showStampCelebration: StateFlow<Boolean> = _showStampCelebration.asStateFlow()

    fun addToCart(item: MenuItem) {
        _cart.update { current ->
            val existing = current[item.id] ?: 0
            current + (item.id to (existing + 1))
        }
    }

    fun removeFromCart(item: MenuItem) {
        _cart.update { current ->
            val existing = current[item.id] ?: 0
            if (existing <= 1) {
                current - item.id
            } else {
                current + (item.id to (existing - 1))
            }
        }
    }

    fun clearCart() {
        _cart.value = emptyMap()
    }

    fun openBill() {
        if (_cart.value.isNotEmpty()) {
            _showBillDialog.value = true
        }
    }

    fun dismissBill() {
        _showBillDialog.value = false
    }

    fun confirmOrderAndEarnStamp(): Boolean {
        _showBillDialog.value = false
        _cart.value = emptyMap()

        // Increment visit stamp (up to 10)
        _visits.update { current ->
            if (current < 10) current + 1 else 10
        }
        _showStampCelebration.value = true
        return true
    }

    fun dismissStampCelebration() {
        _showStampCelebration.value = false
    }

    fun simulateVisit() {
        _visits.update { current ->
            if (current < 10) current + 1 else 1
        }
    }

    fun resetVisits(count: Int = 6) {
        _visits.value = count
    }

    fun getCartItems(): List<CartItem> {
        val currentCart = _cart.value
        return MenuRepository.sampleMenuItems
            .filter { currentCart.containsKey(it.id) && (currentCart[it.id] ?: 0) > 0 }
            .map { CartItem(item = it, quantity = currentCart[it.id] ?: 0) }
    }

    fun getTotalItemCount(): Int {
        return _cart.value.values.sum()
    }

    fun getSubtotal(): Double {
        return getCartItems().sumOf { it.item.price * it.quantity }
    }
}
