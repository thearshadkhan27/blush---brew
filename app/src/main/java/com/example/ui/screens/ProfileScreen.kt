package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BlushBackground
import com.example.ui.theme.BlushBorder
import com.example.ui.theme.BlushSurface
import com.example.ui.theme.BlushSurfaceVariant
import com.example.ui.theme.EspressoMuted
import com.example.ui.theme.EspressoText
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.PlusJakartaFontFamily
import com.example.ui.theme.RoseLight
import com.example.ui.theme.RosePrimary

@Composable
fun ProfileScreen(
    visitsCount: Int = 6,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    var showFavoritesDialog by remember { mutableStateOf(false) }
    var showDietaryDialog by remember { mutableStateOf(false) }
    var showNotificationsDialog by remember { mutableStateOf(false) }

    // Dietary preferences state
    val dietaryOptions = remember {
        mutableStateListOf(
            "Oat milk preference" to true,
            "Low sugar option" to true,
            "Gluten-conscious" to false,
            "Nut allergy notice" to false,
            "Decaf after 3 PM" to false
        )
    }

    var dealsNotification by remember { mutableStateOf(true) }
    var stampsNotification by remember { mutableStateOf(true) }
    var seasonalDrinksNotification by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BlushBackground)
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("profile_screen")
    ) {
        // Customer Profile Header Card
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = BlushSurface),
            border = BorderStroke(1.dp, BlushBorder),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("customer_profile_card")
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                BlushSurface,
                                Color(0xFFFFF0F3)
                            )
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar with Heart badge
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                            .background(RoseLight)
                            .border(2.dp, RosePrimary.copy(alpha = 0.3f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Avatar",
                            tint = RosePrimary,
                            modifier = Modifier.size(42.dp)
                        )

                        // Mini heart badge on bottom right
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(RosePrimary)
                                .align(Alignment.BottomEnd),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(12.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "HELLO THERE",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            letterSpacing = 1.sp,
                            color = RosePrimary
                        )

                        Text(
                            text = "Guest Customer",
                            fontFamily = PlayfairFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = EspressoText
                        )

                        Text(
                            text = "Profile coming soon",
                            fontFamily = PlusJakartaFontFamily,
                            fontSize = 12.sp,
                            color = EspressoMuted
                        )
                    }

                    // Doodle heart tagline
                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = RosePrimary.copy(alpha = 0.8f),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Great coffee\nbrings people\ntogether",
                            fontFamily = PlusJakartaFontFamily,
                            fontSize = 9.sp,
                            color = RosePrimary,
                            lineHeight = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Stats Row (3 Bento cards)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ProfileStatCard(
                icon = Icons.Default.Coffee,
                number = "$visitsCount visits",
                caption = "Thanks for being here!",
                modifier = Modifier.weight(1f)
            )

            ProfileStatCard(
                icon = Icons.Default.CardGiftcard,
                number = "1 reward earned",
                caption = "You're on a roll!",
                modifier = Modifier.weight(1f)
            )

            ProfileStatCard(
                icon = Icons.Default.CalendarToday,
                number = "Member since 2026",
                caption = "A delicious journey ahead",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Menu Options List
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = BlushSurface),
            border = BorderStroke(1.dp, BlushBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                ProfileOptionRow(
                    icon = Icons.Default.Favorite,
                    title = "Favourite items",
                    subtitle = "Your go-to coffees, food and treats",
                    onClick = { showFavoritesDialog = true }
                )

                Divider(color = BlushBorder, thickness = 1.dp)

                ProfileOptionRow(
                    icon = Icons.Default.Restaurant,
                    title = "Dietary preferences",
                    subtitle = "Tell us what works for you",
                    onClick = { showDietaryDialog = true }
                )

                Divider(color = BlushBorder, thickness = 1.dp)

                ProfileOptionRow(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    subtitle = "Choose what you'd like to hear about",
                    onClick = { showNotificationsDialog = true }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Google Review Section
        com.example.ui.components.GoogleReviewCard()

        Spacer(modifier = Modifier.height(16.dp))

        // Bottom Info Banner (Matching Screenshot 4)
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = RoseLight.copy(alpha = 0.5f)),
            border = BorderStroke(1.dp, RosePrimary.copy(alpha = 0.2f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = RosePrimary,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Sample data shown. Accounts are not connected yet.",
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = RosePrimary
                    )
                    Text(
                        text = "This is a preview of your profile. Sign-in will be available soon.",
                        fontFamily = PlusJakartaFontFamily,
                        fontSize = 10.sp,
                        color = EspressoMuted
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Coffee,
                        contentDescription = null,
                        tint = RosePrimary,
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        text = "More good\ndays ahead",
                        fontFamily = PlusJakartaFontFamily,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Medium,
                        color = RosePrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }

    // Favorite Items Dialog
    if (showFavoritesDialog) {
        AlertDialog(
            onDismissRequest = { showFavoritesDialog = false },
            shape = RoundedCornerShape(20.dp),
            containerColor = BlushSurface,
            title = {
                Text(
                    text = "Favourite Items",
                    fontFamily = PlayfairFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = EspressoText
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Your saved customer favorites:",
                        fontFamily = PlusJakartaFontFamily,
                        fontSize = 12.sp,
                        color = EspressoMuted
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(RoseLight)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = RosePrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Rose Latte (₹240)",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = EspressoText
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(RoseLight)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = RosePrimary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Caramel Cheesecake (₹280)",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = EspressoText
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showFavoritesDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = RosePrimary),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Close")
                }
            }
        )
    }

    // Dietary Preferences Dialog
    if (showDietaryDialog) {
        AlertDialog(
            onDismissRequest = { showDietaryDialog = false },
            shape = RoundedCornerShape(20.dp),
            containerColor = BlushSurface,
            title = {
                Text(
                    text = "Dietary Preferences",
                    fontFamily = PlayfairFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = EspressoText
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = "Customize your preparation defaults:",
                        fontFamily = PlusJakartaFontFamily,
                        fontSize = 12.sp,
                        color = EspressoMuted
                    )

                    dietaryOptions.indices.forEach { index ->
                        val (label, isChecked) = dietaryOptions[index]
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    dietaryOptions[index] = label to !isChecked
                                }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { dietaryOptions[index] = label to it },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = RosePrimary,
                                    checkmarkColor = Color.White
                                )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = label,
                                fontFamily = PlusJakartaFontFamily,
                                fontSize = 13.sp,
                                color = EspressoText
                            )
                        }
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showDietaryDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = RosePrimary),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Save Preferences")
                }
            }
        )
    }

    // Notifications Dialog
    if (showNotificationsDialog) {
        AlertDialog(
            onDismissRequest = { showNotificationsDialog = false },
            shape = RoundedCornerShape(20.dp),
            containerColor = BlushSurface,
            title = {
                Text(
                    text = "Notification Settings",
                    fontFamily = PlayfairFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = EspressoText
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Daily Deals & Offers",
                            fontFamily = PlusJakartaFontFamily,
                            fontSize = 13.sp,
                            color = EspressoText
                        )
                        Switch(
                            checked = dealsNotification,
                            onCheckedChange = { dealsNotification = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = RosePrimary)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Loyalty Stamp Alerts",
                            fontFamily = PlusJakartaFontFamily,
                            fontSize = 13.sp,
                            color = EspressoText
                        )
                        Switch(
                            checked = stampsNotification,
                            onCheckedChange = { stampsNotification = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = RosePrimary)
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "New Seasonal Drinks",
                            fontFamily = PlusJakartaFontFamily,
                            fontSize = 13.sp,
                            color = EspressoText
                        )
                        Switch(
                            checked = seasonalDrinksNotification,
                            onCheckedChange = { seasonalDrinksNotification = it },
                            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = RosePrimary)
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = { showNotificationsDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = RosePrimary),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Done")
                }
            }
        )
    }
}

@Composable
private fun ProfileStatCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    number: String,
    caption: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BlushSurface),
        border = BorderStroke(1.dp, BlushBorder),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(RoseLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = RosePrimary,
                    modifier = Modifier.size(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = number,
                fontFamily = PlusJakartaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                color = EspressoText,
                lineHeight = 15.sp
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = caption,
                fontFamily = PlusJakartaFontFamily,
                fontSize = 10.sp,
                color = EspressoMuted,
                lineHeight = 13.sp
            )
        }
    }
}

@Composable
private fun ProfileOptionRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(RoseLight),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = RosePrimary,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = PlusJakartaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = EspressoText
            )
            Text(
                text = subtitle,
                fontFamily = PlusJakartaFontFamily,
                fontSize = 11.sp,
                color = EspressoMuted
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = EspressoMuted,
            modifier = Modifier.size(18.dp)
        )
    }
}
