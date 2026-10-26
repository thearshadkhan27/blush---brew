package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.MenuItem
import com.example.ui.CafeViewModel
import com.example.ui.components.AppTab
import com.example.ui.components.BillDialog
import com.example.ui.components.StampCelebrationDialog
import com.example.ui.components.TopHeaderBar
import com.example.ui.screens.MenuScreen
import com.example.ui.screens.OffersScreen
import com.example.ui.screens.ProductDetailScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.RewardsScreen
import com.example.ui.theme.BlushAndBrewTheme
import com.example.ui.theme.BlushBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlushAndBrewTheme {
                BlushAndBrewApp()
            }
        }
    }
}

@Composable
fun BlushAndBrewApp(
    viewModel: CafeViewModel = viewModel()
) {
    var currentTab by remember { mutableStateOf(AppTab.MENU) }
    var selectedProduct by remember { mutableStateOf<MenuItem?>(null) }

    val cart by viewModel.cart.collectAsStateWithLifecycle()
    val visits by viewModel.visits.collectAsStateWithLifecycle()
    val showBillDialog by viewModel.showBillDialog.collectAsStateWithLifecycle()
    val showStampCelebration by viewModel.showStampCelebration.collectAsStateWithLifecycle()

    // Back handling: If viewing product detail, go back to menu; if on other tab, go to Menu tab
    BackHandler(enabled = selectedProduct != null || currentTab != AppTab.MENU) {
        if (selectedProduct != null) {
            selectedProduct = null
        } else {
            currentTab = AppTab.MENU
        }
    }

    Scaffold(
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopHeaderBar(
                currentTab = currentTab,
                onTabSelected = { tab ->
                    selectedProduct = null
                    currentTab = tab
                },
                modifier = Modifier.testTag("app_top_bar")
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(BlushBackground)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedContent(
                targetState = selectedProduct to currentTab,
                transitionSpec = {
                    (fadeIn() + slideInHorizontally { width -> width / 3 })
                        .togetherWith(fadeOut() + slideOutHorizontally { width -> -width / 3 })
                },
                label = "ScreenTransition"
            ) { (product, tab) ->
                if (product != null) {
                    val quantity = cart[product.id] ?: 0
                    ProductDetailScreen(
                        item = product,
                        quantity = quantity,
                        onAddToCart = { viewModel.addToCart(product) },
                        onRemoveFromCart = { viewModel.removeFromCart(product) },
                        onViewBill = { viewModel.openBill() },
                        onBackClick = { selectedProduct = null },
                        onSelectRelatedItem = { relatedItem ->
                            selectedProduct = relatedItem
                        }
                    )
                } else {
                    when (tab) {
                        AppTab.MENU -> MenuScreen(
                            cart = cart,
                            onAddToCart = { item -> viewModel.addToCart(item) },
                            onRemoveFromCart = { item -> viewModel.removeFromCart(item) },
                            onViewBill = { viewModel.openBill() },
                            onProductClick = { item -> selectedProduct = item },
                            onOfferClick = { _ -> currentTab = AppTab.OFFERS }
                        )
                        AppTab.OFFERS -> OffersScreen(
                            onOfferSelected = { _ -> }
                        )
                        AppTab.REWARDS -> RewardsScreen(
                            currentVisits = visits,
                            onSimulateVisit = { viewModel.simulateVisit() },
                            onResetVisits = { viewModel.resetVisits(6) }
                        )
                        AppTab.PROFILE -> ProfileScreen(
                            visitsCount = visits
                        )
                    }
                }
            }
        }
    }

    // Bill Dialog: shows itemized bill with tax, subtotal, total, and OK action
    if (showBillDialog) {
        val cartItems = viewModel.getCartItems()
        BillDialog(
            cartItems = cartItems,
            subtotal = viewModel.getSubtotal(),
            currentVisits = visits,
            onAddItem = { item -> viewModel.addToCart(item) },
            onRemoveItem = { item -> viewModel.removeFromCart(item) },
            onConfirmOrder = {
                viewModel.confirmOrderAndEarnStamp()
                selectedProduct = null
                currentTab = AppTab.REWARDS
            },
            onDismiss = { viewModel.dismissBill() }
        )
    }

    // Stamp Celebration Dialog: appears after clicking OK on bill
    if (showStampCelebration) {
        StampCelebrationDialog(
            newVisitCount = visits,
            onDismiss = {
                viewModel.dismissStampCelebration()
            },
            onViewRewards = {
                viewModel.dismissStampCelebration()
                currentTab = AppTab.REWARDS
            }
        )
    }
}
