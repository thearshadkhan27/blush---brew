package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BadgeType
import com.example.data.MenuItem
import com.example.ui.theme.AvailableGreen
import com.example.ui.theme.AvailableGreenBg
import com.example.ui.theme.BlushBorder
import com.example.ui.theme.BlushSurface
import com.example.ui.theme.EspressoMuted
import com.example.ui.theme.EspressoText
import com.example.ui.theme.PlusJakartaFontFamily
import com.example.ui.theme.RoseLight
import com.example.ui.theme.RosePrimary
import com.example.ui.theme.SoldOutGray
import com.example.ui.theme.SoldOutGrayBg

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductCard(
    item: MenuItem,
    quantity: Int = 0,
    onAddToCart: () -> Unit,
    onRemoveFromCart: () -> Unit = {},
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = BlushSurface),
        border = BorderStroke(1.dp, if (quantity > 0) RosePrimary.copy(alpha = 0.5f) else BlushBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp, pressedElevation = 4.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag("product_card_${item.id}")
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Image with badge & quantity indicator overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                )

                // Top Badge
                if (item.badge != BadgeType.NONE) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.TopStart)
                    ) {
                        ProductBadge(badge = item.badge)
                    }
                }

                // If selected in bill, show quantity badge on top right
                if (quantity > 0) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.TopEnd)
                            .clip(CircleShape)
                            .background(RosePrimary)
                            .size(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "$quantity",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }

            // Product Details
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                // Name & Price Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = item.name,
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = EspressoText,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "₹${item.price.toInt()}",
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = RosePrimary
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                // Description
                Text(
                    text = item.description,
                    fontFamily = PlusJakartaFontFamily,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = EspressoMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Bottom Row: Dietary tag and Interactive Plus / Quantity Button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Tag
                    val firstTag = item.dietaryTags.firstOrNull()
                    if (firstTag != null) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(RoseLight.copy(alpha = 0.6f))
                                .padding(horizontal = 7.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = firstTag,
                                fontFamily = PlusJakartaFontFamily,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Medium,
                                color = RosePrimary,
                                maxLines = 1
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    // Plus / Quantity Selector
                    if (item.badge == BadgeType.SOLD_OUT) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = SoldOutGrayBg,
                            modifier = Modifier.height(32.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            ) {
                                Text(
                                    text = "Sold out",
                                    fontFamily = PlusJakartaFontFamily,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = SoldOutGray
                                )
                            }
                        }
                    } else if (quantity > 0) {
                        // - / qty / + pill
                        Surface(
                            shape = RoundedCornerShape(18.dp),
                            color = RoseLight,
                            border = BorderStroke(1.dp, RosePrimary.copy(alpha = 0.3f)),
                            modifier = Modifier.height(34.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            ) {
                                Surface(
                                    onClick = onRemoveFromCart,
                                    shape = CircleShape,
                                    color = Color.White,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .testTag("decrease_item_${item.id}")
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.Remove,
                                            contentDescription = "Remove one",
                                            tint = RosePrimary,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }

                                Text(
                                    text = "$quantity",
                                    fontFamily = PlusJakartaFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = RosePrimary,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )

                                Surface(
                                    onClick = onAddToCart,
                                    shape = CircleShape,
                                    color = RosePrimary,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .testTag("increase_item_${item.id}")
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = "Add one",
                                            tint = Color.White,
                                            modifier = Modifier.size(14.dp)
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        // Prominent Add "+" Button
                        Surface(
                            onClick = onAddToCart,
                            shape = CircleShape,
                            color = RosePrimary,
                            shadowElevation = 2.dp,
                            modifier = Modifier
                                .size(34.dp)
                                .testTag("add_item_button_${item.id}")
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add ${item.name} to order",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProductBadge(badge: BadgeType, modifier: Modifier = Modifier) {
    val (bgColor, textColor, label, hasStar) = when (badge) {
        BadgeType.BESTSELLER -> Tuple4(RosePrimary, Color.White, "Bestseller", true)
        BadgeType.AVAILABLE -> Tuple4(AvailableGreenBg, AvailableGreen, "Available", false)
        BadgeType.AVAILABLE_TODAY -> Tuple4(AvailableGreenBg, AvailableGreen, "Available today", false)
        BadgeType.SOLD_OUT -> Tuple4(SoldOutGrayBg, SoldOutGray, "Sold out today", false)
        BadgeType.NONE -> Tuple4(Color.Transparent, Color.Transparent, "", false)
    }

    if (badge == BadgeType.NONE) return

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(bgColor)
            .padding(horizontal = 9.dp, vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (hasStar) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(11.dp)
                )
                Spacer(modifier = Modifier.width(3.dp))
            }
            Text(
                text = label,
                fontFamily = PlusJakartaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = textColor
            )
        }
    }
}

private data class Tuple4<A, B, C, D>(val a: A, val b: B, val c: C, val d: D)
