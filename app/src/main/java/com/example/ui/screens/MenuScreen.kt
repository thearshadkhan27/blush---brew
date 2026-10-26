package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.Category
import com.example.data.MenuItem
import com.example.data.MenuRepository
import com.example.ui.components.HeroBanner
import com.example.ui.components.ProductCard
import com.example.ui.theme.BlushBackground
import com.example.ui.theme.BlushBorder
import com.example.ui.theme.BlushSurface
import com.example.ui.theme.EspressoMuted
import com.example.ui.theme.EspressoText
import com.example.ui.theme.PlusJakartaFontFamily
import com.example.ui.theme.RoseLight
import com.example.ui.theme.RosePrimary

@Composable
fun MenuScreen(
    cart: Map<String, Int>,
    onAddToCart: (MenuItem) -> Unit,
    onRemoveFromCart: (MenuItem) -> Unit,
    onViewBill: () -> Unit,
    onProductClick: (MenuItem) -> Unit,
    onOfferClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(Category.ALL) }

    val totalItemsCount = remember(cart) { cart.values.sum() }
    val totalCartPrice = remember(cart) {
        MenuRepository.sampleMenuItems
            .filter { cart.containsKey(it.id) }
            .sumOf { it.price * (cart[it.id] ?: 0) }
    }

    val filteredItems by remember(searchQuery, selectedCategory) {
        derivedStateOf {
            MenuRepository.sampleMenuItems.filter { item ->
                val matchesCategory = selectedCategory == Category.ALL || item.category == selectedCategory
                val matchesSearch = searchQuery.isBlank() ||
                        item.name.contains(searchQuery, ignoreCase = true) ||
                        item.description.contains(searchQuery, ignoreCase = true) ||
                        item.dietaryTags.any { it.contains(searchQuery, ignoreCase = true) }
                matchesCategory && matchesSearch
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 160.dp),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = if (totalItemsCount > 0) 80.dp else 24.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(BlushBackground)
                .testTag("menu_screen_grid")
        ) {
            // Hero Section & Bento Offers (Show when not actively searching)
            if (searchQuery.isBlank() && selectedCategory == Category.ALL) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    HeroBanner(
                        onFeaturedClick = {
                            val roseLatte = MenuRepository.sampleMenuItems.firstOrNull { it.id == "rose_latte" }
                            if (roseLatte != null) onProductClick(roseLatte)
                        },
                        onOfferClick = onOfferClick
                    )
                }
            }

            // Search Bar & Category Filter Bar
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    // Search TextField
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = {
                            Text(
                                text = "Search the menu...",
                                fontFamily = PlusJakartaFontFamily,
                                fontSize = 14.sp,
                                color = EspressoMuted
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = RosePrimary
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotBlank()) {
                                IconButton(onClick = { searchQuery = "" }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear search",
                                        tint = EspressoMuted
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = BlushSurface,
                            unfocusedContainerColor = BlushSurface,
                            focusedBorderColor = RosePrimary,
                            unfocusedBorderColor = BlushBorder
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("search_menu_input")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Category Filter Chips Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Category.values().forEach { category ->
                            val isSelected = category == selectedCategory
                            Surface(
                                onClick = { selectedCategory = category },
                                shape = RoundedCornerShape(20.dp),
                                color = if (isSelected) RosePrimary else BlushSurface,
                                border = if (isSelected) null else BorderStroke(1.dp, BlushBorder),
                                modifier = Modifier
                                    .testTag("category_chip_${category.name.lowercase()}")
                                    .height(36.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                ) {
                                    Text(
                                        text = category.displayName,
                                        fontFamily = PlusJakartaFontFamily,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        fontSize = 13.sp,
                                        color = if (isSelected) Color.White else EspressoText
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Section Title
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (selectedCategory == Category.ALL) "Our Specialty Menu" else "${selectedCategory.displayName} Selection",
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp,
                        color = EspressoText
                    )

                    Text(
                        text = "${filteredItems.size} items",
                        fontFamily = PlusJakartaFontFamily,
                        fontSize = 12.sp,
                        color = EspressoMuted
                    )
                }
            }

            // Product Cards with Plus icon
            if (filteredItems.isEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(RoseLight),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SearchOff,
                                contentDescription = null,
                                tint = RosePrimary,
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "No delicious items found",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = EspressoText
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Try adjusting your search or category filter.",
                            fontFamily = PlusJakartaFontFamily,
                            fontSize = 13.sp,
                            color = EspressoMuted
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                searchQuery = ""
                                selectedCategory = Category.ALL
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = RosePrimary),
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier.testTag("reset_filters_button")
                        ) {
                            Text(
                                text = "Reset Filters",
                                fontFamily = PlusJakartaFontFamily,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            } else {
                items(filteredItems, key = { it.id }) { item ->
                    val quantity = cart[item.id] ?: 0
                    ProductCard(
                        item = item,
                        quantity = quantity,
                        onAddToCart = { onAddToCart(item) },
                        onRemoveFromCart = { onRemoveFromCart(item) },
                        onClick = { onProductClick(item) }
                    )
                }
            }

            // Google Review Section
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(12.dp))
                com.example.ui.components.GoogleReviewCard()
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        // Floating Order Bill Bar when customer selects items
        AnimatedVisibility(
            visible = totalItemsCount > 0,
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(26.dp),
                colors = CardDefaults.cardColors(containerColor = RosePrimary),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                onClick = onViewBill,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .testTag("view_bill_floating_bar")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = 0.25f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.ReceiptLong,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(
                                text = "$totalItemsCount ${if (totalItemsCount == 1) "item" else "items"} selected",
                                fontFamily = PlusJakartaFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.White
                            )
                            Text(
                                text = "₹${totalCartPrice.toInt()} subtotal",
                                fontFamily = PlusJakartaFontFamily,
                                fontSize = 11.sp,
                                color = Color.White.copy(alpha = 0.85f)
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "View Bill",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
