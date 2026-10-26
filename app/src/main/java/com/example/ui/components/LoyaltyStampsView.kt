package com.example.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BlushBorder
import com.example.ui.theme.BlushSurface
import com.example.ui.theme.EspressoMuted
import com.example.ui.theme.EspressoText
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.PlusJakartaFontFamily
import com.example.ui.theme.RoseLight
import com.example.ui.theme.RosePrimary
import com.example.ui.theme.StarGold
import com.example.ui.theme.StarGoldLight

@Composable
fun LoyaltyStampsCard(
    currentVisits: Int,
    modifier: Modifier = Modifier
) {
    val totalTarget = 10
    val progress = (currentVisits / totalTarget.toFloat()).coerceIn(0f, 1f)

    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .fillMaxWidth()
            .testTag("premium_loyalty_stamps_card")
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFE25278),
                            Color(0xFFD63B66),
                            Color(0xFFB5234A)
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Top Header Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.25f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "BLUSH & BREW CAFE",
                                fontFamily = PlusJakartaFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                letterSpacing = 1.sp,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Loyalty Stamp Card",
                            fontFamily = PlayfairFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = Color.White
                        )

                        Text(
                            text = "$currentVisits of $totalTarget visits collected",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.95f)
                        )
                    }

                    // Watermark Badge
                    Column(horizontalAlignment = Alignment.End) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White.copy(alpha = 0.2f),
                            modifier = Modifier.padding(bottom = 4.dp)
                        ) {
                            Text(
                                text = "${(progress * 100).toInt()}% Done",
                                fontFamily = PlusJakartaFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                        Text(
                            text = "Good Food • Brighter Days",
                            fontFamily = PlusJakartaFontFamily,
                            fontSize = 9.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Progress Bar
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 10 Stamps Display: 2 Rows of 5 for spacious, premium layout
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color.White.copy(alpha = 0.12f))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Row 1: Stamps 1 to 5
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (i in 1..5) {
                            BetterStampSeal(
                                stampNumber = i,
                                isStamped = i <= currentVisits,
                                isMilestone = i == 5,
                                milestoneLabel = "Cookie"
                            )
                        }
                    }

                    // Row 2: Stamps 6 to 10
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        for (i in 6..10) {
                            BetterStampSeal(
                                stampNumber = i,
                                isStamped = i <= currentVisits,
                                isMilestone = i == 10,
                                milestoneLabel = "Coffee"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Footer description
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (currentVisits < 5) {
                            "Visit ${5 - currentVisits} more times for a Free Cookie 🍪"
                        } else if (currentVisits < 10) {
                            "Visit ${10 - currentVisits} more times for Free Coffee ☕"
                        } else {
                            "🎉 All 10 stamps collected! Claim your rewards."
                        },
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        color = Color.White
                    )

                    Icon(
                        imageVector = Icons.Default.Stars,
                        contentDescription = null,
                        tint = StarGoldLight,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BetterStampSeal(
    stampNumber: Int,
    isStamped: Boolean,
    isMilestone: Boolean = false,
    milestoneLabel: String = "",
    modifier: Modifier = Modifier
) {
    val scale by animateFloatAsState(
        targetValue = if (isStamped) 1f else 0.92f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "stampScale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .scale(scale)
                .clip(CircleShape)
                .background(
                    if (isStamped) {
                        Brush.radialGradient(
                            colors = if (isMilestone) {
                                listOf(StarGold, Color(0xFFC73859))
                            } else {
                                listOf(Color.White, Color(0xFFFFF0F3))
                            }
                        )
                    } else {
                        Brush.radialGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.15f),
                                Color.White.copy(alpha = 0.05f)
                            )
                        )
                    }
                )
                .border(
                    width = if (isStamped) 2.dp else 1.dp,
                    color = if (isStamped) {
                        if (isMilestone) StarGoldLight else Color.White
                    } else {
                        Color.White.copy(alpha = 0.4f)
                    },
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isStamped) {
                // Stamped Wax-Seal Look
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = if (isMilestone && stampNumber == 5) Icons.Default.Cookie
                        else if (isMilestone && stampNumber == 10) Icons.Default.Coffee
                        else Icons.Default.Favorite,
                        contentDescription = "Stamp $stampNumber Stamped",
                        tint = if (isMilestone) Color.White else RosePrimary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "✓",
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 10.sp,
                        color = if (isMilestone) Color.White else RosePrimary,
                        lineHeight = 10.sp
                    )
                }
            } else {
                // Unstamped outline with stamp number & watermark
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "#$stampNumber",
                        fontFamily = PlusJakartaFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                    if (isMilestone) {
                        Text(
                            text = if (stampNumber == 5) "🍪" else "☕",
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(3.dp))

        // Small subtext
        Text(
            text = if (isMilestone) milestoneLabel else "#$stampNumber",
            fontFamily = PlusJakartaFontFamily,
            fontWeight = if (isMilestone) FontWeight.Bold else FontWeight.Normal,
            fontSize = 9.sp,
            color = if (isMilestone) StarGoldLight else Color.White.copy(alpha = 0.85f),
            textAlign = TextAlign.Center
        )
    }
}
