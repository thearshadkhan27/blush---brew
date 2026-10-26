package com.example.ui.components

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.BlushBorder
import com.example.ui.theme.BlushSurface
import com.example.ui.theme.EspressoMuted
import com.example.ui.theme.EspressoText
import com.example.ui.theme.PlayfairFontFamily
import com.example.ui.theme.PlusJakartaFontFamily
import com.example.ui.theme.RoseLight
import com.example.ui.theme.RosePrimary
import com.example.ui.theme.StarGold

@Composable
fun StampCelebrationDialog(
    newVisitCount: Int,
    onDismiss: () -> Unit,
    onViewRewards: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(26.dp),
            color = BlushSurface,
            shadowElevation = 10.dp,
            border = BorderStroke(1.dp, BlushBorder),
            modifier = modifier
                .fillMaxWidth()
                .padding(14.dp)
                .testTag("stamp_celebration_dialog")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Row: Dismiss icon button in the right upper corner
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(RoseLight),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Celebration,
                                contentDescription = null,
                                tint = RosePrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Blush & Brew Loyalty",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = RosePrimary
                        )
                    }

                    // Plus / Close icon in right upper corner to dismiss/remove the interface
                    Surface(
                        onClick = onDismiss,
                        shape = CircleShape,
                        color = RoseLight,
                        border = BorderStroke(1.dp, RosePrimary.copy(alpha = 0.2f)),
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("dismiss_stamp_dialog_button")
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close visit stamps",
                                tint = RosePrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Title & Subtitle
                Text(
                    text = "Visit Stamp Earned!",
                    fontFamily = PlayfairFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = EspressoText
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Stamp #$newVisitCount has been added to your digital card.",
                    fontFamily = PlusJakartaFontFamily,
                    fontSize = 13.sp,
                    textAlign = TextAlign.Center,
                    color = EspressoMuted
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Better Visual Digital Stamp Card (2 Rows of 5)
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.Transparent,
                    border = BorderStroke(1.dp, BlushBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFE25278),
                                        Color(0xFFC72856)
                                    )
                                )
                            )
                            .padding(14.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "$newVisitCount of 10 Visits Stamped",
                                    fontFamily = PlusJakartaFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = Color.White
                                )

                                Text(
                                    text = "${newVisitCount * 10}% Complete",
                                    fontFamily = PlusJakartaFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp,
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Row 1: Stamps 1 to 5
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                for (i in 1..5) {
                                    DialogStampSeal(
                                        stampNumber = i,
                                        isStamped = i <= newVisitCount,
                                        isMilestone = i == 5,
                                        milestoneIcon = Icons.Default.Cookie
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            // Row 2: Stamps 6 to 10
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                for (i in 6..10) {
                                    DialogStampSeal(
                                        stampNumber = i,
                                        isStamped = i <= newVisitCount,
                                        isMilestone = i == 10,
                                        milestoneIcon = Icons.Default.Coffee
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Milestone Notification
                if (newVisitCount >= 5) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = RoseLight,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = if (newVisitCount >= 10) Icons.Default.Coffee else Icons.Default.Cookie,
                                contentDescription = null,
                                tint = RosePrimary,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (newVisitCount >= 10) "🎉 Free Coffee Milestone Unlocked!" else "🍪 Free Cookie Milestone Unlocked!",
                                fontFamily = PlusJakartaFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = RosePrimary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))
                }

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onViewRewards,
                        colors = ButtonDefaults.buttonColors(containerColor = RosePrimary),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("view_stamps_button")
                    ) {
                        Text(
                            text = "Show Full Loyalty Rewards",
                            fontFamily = PlusJakartaFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DialogStampSeal(
    stampNumber: Int,
    isStamped: Boolean,
    isMilestone: Boolean,
    milestoneIcon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Box(
        modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(
                if (isStamped) {
                    if (isMilestone) Color(0xFFFFD54F) else Color.White
                } else {
                    Color.White.copy(alpha = 0.2f)
                }
            )
            .border(
                width = if (isStamped) 1.5.dp else 1.dp,
                color = if (isStamped) Color.White else Color.White.copy(alpha = 0.4f),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isStamped) {
            Icon(
                imageVector = if (isMilestone) milestoneIcon else Icons.Default.Favorite,
                contentDescription = "Stamp $stampNumber",
                tint = if (isMilestone) Color(0xFF6A1B29) else RosePrimary,
                modifier = Modifier.size(18.dp)
            )
        } else {
            Text(
                text = "#$stampNumber",
                fontFamily = PlusJakartaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}
