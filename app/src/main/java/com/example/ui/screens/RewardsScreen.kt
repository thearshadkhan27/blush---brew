package com.example.ui.screens

import com.example.ui.components.LoyaltyStampsCard
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.AvailableGreen
import com.example.ui.theme.AvailableGreenBg
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
fun RewardsScreen(
    currentVisits: Int,
    onSimulateVisit: () -> Unit,
    onResetVisits: () -> Unit,
    modifier: Modifier = Modifier
) {
    val totalTarget = 10
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BlushBackground)
            .verticalScroll(scrollState)
            .padding(16.dp)
            .testTag("rewards_screen")
    ) {
        // Premium Enhanced Digital Loyalty Card
        LoyaltyStampsCard(currentVisits = currentVisits)

        Spacer(modifier = Modifier.height(14.dp))

        // Interactive Testing Bar
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = BlushSurface),
            border = BorderStroke(1.dp, BlushBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = RosePrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Interactive Visit Simulator",
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = EspressoText
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Confirm an order bill or tap below to add stamps to your loyalty card.",
                    fontFamily = PlusJakartaFontFamily,
                    fontSize = 11.sp,
                    color = EspressoMuted
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onSimulateVisit,
                        colors = ButtonDefaults.buttonColors(containerColor = RosePrimary),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("simulate_visit_button")
                    ) {
                        Text(
                            text = if (currentVisits >= totalTarget) "Reset to 1" else "+1 Visit Stamp",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }

                    OutlinedButton(
                        onClick = onResetVisits,
                        border = BorderStroke(1.dp, BlushBorder),
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier
                            .weight(0.8f)
                            .testTag("reset_visits_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            tint = EspressoMuted,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Reset (6)",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            color = EspressoText
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Your Milestones Section
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = BlushSurface),
            border = BorderStroke(1.dp, BlushBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Your Milestones",
                    fontFamily = PlayfairFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = EspressoText
                )
                Text(
                    text = "Small visits. Sweeter rewards.",
                    fontFamily = PlusJakartaFontFamily,
                    fontSize = 12.sp,
                    color = EspressoMuted,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Milestone 1 (Visit 5)
                val isVisit5Done = currentVisits >= 5
                MilestoneRow(
                    icon = Icons.Default.Cookie,
                    title = "Visit 5: Free cookie",
                    subtitle = "A little treat on us.",
                    isCompleted = isVisit5Done
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Milestone 2 (Visit 10)
                val isVisit10Done = currentVisits >= 10
                MilestoneRow(
                    icon = Icons.Default.Coffee,
                    title = "Visit 10: Free coffee",
                    subtitle = "A bigger thank you for being here.",
                    isCompleted = isVisit10Done
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Available Rewards Section
        Text(
            text = "Available Rewards",
            fontFamily = PlayfairFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = EspressoText
        )
        Text(
            text = "Your delicious rewards, earned with every visit.",
            fontFamily = PlusJakartaFontFamily,
            fontSize = 12.sp,
            color = EspressoMuted,
            modifier = Modifier.padding(top = 2.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Rewards Cards Row / Bento
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Reward 1: Free Cookie
            val isCookieEarned = currentVisits >= 5
            RewardItemCard(
                name = "Free cookie",
                statusText = if (isCookieEarned) "✓ Earned" else "${5 - currentVisits} visits to go",
                isEarned = isCookieEarned,
                description = if (isCookieEarned) "You've reached 5 visits! Enjoy a free cookie on us." else "Reach 5 visits to unlock your free cookie treat.",
                imageRes = R.drawable.chocolate_cookie_1792871109376,
                modifier = Modifier.weight(1f)
            )

            // Reward 2: Free Coffee
            val isCoffeeEarned = currentVisits >= 10
            val visitsRemainingForCoffee = (10 - currentVisits).coerceAtLeast(0)
            RewardItemCard(
                name = "Free coffee",
                statusText = if (isCoffeeEarned) "✓ Earned" else "$visitsRemainingForCoffee visits to go",
                isEarned = isCoffeeEarned,
                description = if (isCoffeeEarned) "Milestone achieved! Enjoy any barista coffee on us." else "Just $visitsRemainingForCoffee more visits until your free coffee!",
                imageRes = R.drawable.hero_rose_latte_1792871045270,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // How to Redeem Section
        Card(
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = BlushSurface),
            border = BorderStroke(1.dp, BlushBorder),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "How to redeem",
                    fontFamily = PlayfairFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = EspressoText
                )
                Text(
                    text = "It's easy to enjoy your rewards.",
                    fontFamily = PlusJakartaFontFamily,
                    fontSize = 12.sp,
                    color = EspressoMuted,
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Step 1
                RedeemStepRow(
                    stepNumber = "1",
                    icon = Icons.Default.QrCode2,
                    title = "Show this card to our staff at the counter.",
                    description = "Let us know you're redeeming your reward."
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Step 2
                RedeemStepRow(
                    stepNumber = "2",
                    icon = Icons.Default.Store,
                    title = "Staff confirm your reward in store.",
                    description = "Our team will apply your reward on the spot."
                )
            }
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}

@Composable
private fun MilestoneRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    isCompleted: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(if (isCompleted) RoseLight else BlushSurfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isCompleted) RosePrimary else EspressoMuted,
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = PlusJakartaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = EspressoText
            )
            Text(
                text = subtitle,
                fontFamily = PlusJakartaFontFamily,
                fontSize = 11.sp,
                color = EspressoMuted
            )
        }

        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(CircleShape)
                .background(if (isCompleted) RosePrimary else Color.Transparent)
                .border(
                    width = 1.5.dp,
                    color = if (isCompleted) RosePrimary else BlushBorder,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = Color.White,
                    modifier = Modifier.size(15.dp)
                )
            }
        }
    }
}

@Composable
private fun RewardItemCard(
    name: String,
    statusText: String,
    isEarned: Boolean,
    description: String,
    @androidx.annotation.DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BlushSurface),
        border = BorderStroke(1.dp, if (isEarned) RosePrimary.copy(alpha = 0.4f) else BlushBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            )

            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = name,
                    fontFamily = PlusJakartaFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = EspressoText
                )

                Spacer(modifier = Modifier.height(4.dp))

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isEarned) AvailableGreenBg else RoseLight)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = statusText,
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                        color = if (isEarned) AvailableGreen else RosePrimary
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = description,
                    fontFamily = PlusJakartaFontFamily,
                    fontSize = 11.sp,
                    lineHeight = 15.sp,
                    color = EspressoMuted
                )
            }
        }
    }
}

@Composable
private fun RedeemStepRow(
    stepNumber: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(RosePrimary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stepNumber,
                fontFamily = PlusJakartaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontFamily = PlusJakartaFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp,
                color = EspressoText
            )
            Text(
                text = description,
                fontFamily = PlusJakartaFontFamily,
                fontSize = 11.sp,
                color = EspressoMuted
            )
        }
    }
}
